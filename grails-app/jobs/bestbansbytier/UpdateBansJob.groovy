package bestbansbytier

import jline.internal.Log


class UpdateBansJob {
    BanCalculatorService banCalculatorService
    static triggers = {
        //simple name:'startUpTrigger', startDelay:10000, repeatInterval: 30000, repeatCount: 0
        cron name: 'dailyTrigger', cronExpression: "0 0 5 * * ?"
    }

    def execute() {
        System.out.println("Running ban calculation job")
        banCalculatorService.calculateBans()
    }
}
