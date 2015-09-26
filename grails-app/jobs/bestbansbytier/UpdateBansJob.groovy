package bestbansbytier

import jline.internal.Log


class UpdateBansJob {
    BanCalculatorService banCalculatorService
    static triggers = {
        simple name:'startUpTrigger', startDelay:10000, repeatInterval: 30000, repeatCount: 0
        cron name: 'dailyTrigger', cronExpression: "0 0 5 * * ?"
    }

    def execute() {
        System.out.println("Running ban calculation job")
        List<ServerRegions> failedRegions = []
        ServerRegions.each { region ->
            try {
                banCalculatorService.calculateData(region)
                banCalculatorService.assignRanks(region)
            }
            catch(e) {
                System.out.println("Failed regions ${region} first time \n ${e.message}")
                log.error("Failed regions ${region} first time \n ${e.message}")
                failedRegions.add(region)
            }
        }

        def trialCount = 0
        while(failedRegions && trialCount < 100) {
            Thread.sleep(10000) // Sleep each tier just for safety on parsing win rates from op.gg
            def currentRegion = failedRegions[0]
            try {
                banCalculatorService.calculateData(currentRegion)
                banCalculatorService.assignRanks(currentRegion)
                failedRegions.remove(0)
            }
            catch(e){
                trialCount += 1
                System.out.println("Failed regions ${currentRegion} again \n ${e.message}")
                log.error("Failed regions ${currentRegion} again \n ${e.message}")
            }
        }

        System.out.println("Finished ban calculation ${new Date()}")
        log.info("Finished ban calculation ${new Date()}")
    }
}
