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
                System.out.println("Failed regions ${region} first time")
                log.error("Failed regions ${region} first time")
                failedRegions.add(region)
            }
        }

        while(failedRegions) {
            Thread.sleep(2000) // Sleep each tier just for safety on parsing win rates from op.gg
            def currentRegion = failedRegions[0]
            try {
                banCalculatorService.calculateData(currentRegion)
                banCalculatorService.assignRanks(currentRegion)
                failedRegions.remove(0)
            }
            catch(e){
                System.out.println("Failed regions ${currentRegion} again")
                log.error("Failed regions ${currentRegion} again")
            }
        }
    }
}
