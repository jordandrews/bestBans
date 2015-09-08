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
                failedRegions.add(region)
            }
        }

        def currentRegion = null
        def currentRegionAttempts = 0
        while(failedRegions) {
            if(failedRegions[0] != currentRegion) {
                currentRegion = failedRegions[0]
                currentRegionAttempts = 0
            }
            else {
                Thread.sleep(2000) // Sleep each tier just for safety on parsing win rates from op.gg
                currentRegionAttempts += 1
                if(currentRegionAttempts >= 5) {
                    System.out.println("Unable to fetch data for ${currentRegion} during FINAL ATTEMPT")
                    log.error("Unable to fetch data for ${currentRegion} during FINAL ATTEMPT")
                    failedRegions.remove(0)
                }
            }
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
