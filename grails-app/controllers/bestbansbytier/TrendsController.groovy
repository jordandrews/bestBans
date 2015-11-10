package bestbansbytier

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

class TrendsController {

    def index() {}

    def collectDataPoints() {
        session.region = session.region ?: ServerRegions.NA

        Date today = new Date().clearTime()
        def dataPoints = []// = [[new Date('2015/01/01').format('MMM DD'), 2, 21,6,7,-3],    [new Date('2015/01/02').format('MMM DD'), 10, 5,6,9,5],   [new Date('2015/01/03').format('MMM DD'), 23, 12,3,9,8],  [new Date('2015/01/04').format('MMM DD'), 17, 9,4,0,2],   [new Date('2015/01/05').format('MMM DD'), 18, 10,7,11,6],  [new Date('2015/01/06').format('MMM DD'), 9, 9,6,9,3]]
//        if(params.champion.toUpperCase() == 'AHRI')  {
//            val = [[new Date('2015/02/01').format('MMM DD'), 4, 1,36,47,-33],    [new Date('2015/02/02').format('MMM DD'), 10, 5,6,9,5],   [new Date('2015/02/03').format('MMM DD'), 23, 12,3,9,8],  [new Date('2015/02/04').format('MMM DD'), 17, 9,4,0,2],   [new Date('2015/02/05').format('MMM DD'), 18, 10,7,11,6],  [new Date('2015/02/06').format('MMM DD'), 9, 9,6,9,3]]
//        }
        def champEntries = ChampData.findAllByCreateDateBetweenAndChampionAndRegion((today-30) as Date, today, params.champion, session.region)

        champEntries*.createDate.unique().each{ entryDate ->
            List dayEntry = [entryDate.format('MMM-dd-YY')]
            RankTiers.each{ tier ->
                def champEntry = champEntries.find{it.tier == tier && it.createDate == entryDate}
                dayEntry.add(champEntry?.influence ?: 0)
            }
            dataPoints.add(dayEntry)
        }

        //return render(text: val, model: [dataPoints: val], status: HttpServletResponse.SC_OK, contentType: "text/json") as JSON
        render text: [dataPoints: dataPoints] as JSON, contentType: 'application/json'
    }
}
