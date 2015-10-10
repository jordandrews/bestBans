package bestbansbytier

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

class TrendsController {

    def index() {}

    def collectDataPoints() {
        def val = [[new Date('2015-01-01'), 2, 21,6,7,-3],    [new Date('2015-01-02'), 10, 5,6,9,5],   [new Date('2015-01-03'), 23, 12,3,9,8],  [new Date('2015-01-04'), 17, 9,4,0,2],   [new Date('2015-01-05'), 18, 10,7,11,6],  [new Date('2015-01-06'), 9, 9,6,9,3]]
        return render(text: params.champion, model: [dataPoints: val], status: HttpServletResponse.SC_OK) as JSON
    }
}
