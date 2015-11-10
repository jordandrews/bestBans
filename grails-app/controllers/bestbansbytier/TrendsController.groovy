package bestbansbytier

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

class TrendsController {

    def index() {}

    def collectDataPoints() {
        session.region = session.region ?: ServerRegions.NA

        Date today = new Date().clearTime()
        def dataPoints = []
        def champEntries = ChampData.findAllByCreateDateBetweenAndChampionAndRegion((today-30) as Date, today, params.champion, session.region)

        champEntries*.createDate.unique().each{ entryDate ->
            List dayEntry = [entryDate.format('MMM-dd-YY')]
            RankTiers.each{ tier ->
                def champEntry = champEntries.find{it.tier == tier && it.createDate == entryDate}
                dayEntry.add(champEntry ? (champEntry["${params.stat}"] ?: 0) : 0)
            }
            dataPoints.add(dayEntry)
        }

        //return render(text: val, model: [dataPoints: val], status: HttpServletResponse.SC_OK, contentType: "text/json") as JSON
        render text: [dataPoints: dataPoints] as JSON, contentType: 'application/json'
    }
}
