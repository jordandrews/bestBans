<%@ page import="bestbansbytier.ChampData; bestbansbytier.ServerRegions; java.math.RoundingMode; bestbansbytier.RankTiers" contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <meta name='layout' content="main"/>
    <title> Best Bans League Of Legends</title>
</head>

<body>
<div class="row col-md-4">
    <form id="searchForm">
        <fieldset >
            <legend>Search</legend>
            <label for="champion">Champion</label>
            <g:select name="champion" id='champSelect' type="select" from="${ChampData.getChampSelect()}" optionValue="displayName" optionKey="value"/>
            <g:select name="stat" id='stat' type="select" from="${['influence', 'pickrate', 'banrate', 'winrate']}"/>
            <button name="button" id="searchButton" class="btn-primary">search</button>
        </fieldset>
    </form>
</div>
<br/>
<div class="row col-md-12 pull-left">
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <div id="chart_div"></div>
</div>
</body>
</html>

<asset:script>
    $(document).ready(function(){
        $('.my-tool-tip').tooltip()
    });

     google.load('visualization', '1', {packages: ['corechart', 'line']});
     google.setOnLoadCallback(drawLineColors);

    $('#searchButton').click(function (e) {
        e.preventDefault()
        google.load('visualization', '1', {packages: ['corechart', 'line']});
             //google.setOnLoadCallback(drawLineColors);
             drawLineColors();

    });

    function drawLineColors() {
          var data = new google.visualization.DataTable();
          data.addColumn('string', 'Day');
          data.addColumn('number', 'Bronze');
          data.addColumn('number', 'Silver');
          data.addColumn('number', 'Gold');
          data.addColumn('number', 'Platinum');
          data.addColumn('number', 'Diamond');

                    var options = {
            title: 'CHAMPION',
            titleTextStyle: {
                color: 'white'
            },
            hAxis: {
              title: 'Day',
              titleTextStyle: {
                color: '#FFFFFF'
              },
              textStyle: {
                color: '#FFFFFF'
              },
              baselineColor: '#FFFFFF'
            },
            vAxis: {
              title: 'Influence',
              titleTextStyle: {
                color: '#FFFFFF'
              },
              textStyle: {
                color: '#FFFFFF'
              },
               baselineColor: '#FFFFFF'
            },
            legend: {
                textStyle: {color: '#FFFFFF'}
            },
            colors: ['brown', 'silver', 'gold', 'red', 'blue'],
            backgroundColor: '#222222',
            height: 600
          };

          var action = "${createLink(controller:'trends', action: 'collectDataPoints')}";
          var formData = $('#searchForm').serializeArray();
                    $.ajax({
                        url: action,
                        type: 'GET',
                        dataType: "json",
                        data: formData,
                        context: $(this),
                        global: false
                    })
                    .success(function (response) {
                        $('#messages').html(response.dataPoints);
                        data.addRows(response.dataPoints);
                        //[
                          //  [new Date('2015-01-01'), 2, 21,6,7,-3],    [new Date('2015-01-02'), 10, 5,6,9,5],   [new Date('2015-01-03'), 23, 12,3,9,8],  [new Date('2015-01-04'), 17, 9,4,0,2],   [new Date('2015-01-05'), 18, 10,7,11,6],  [new Date('2015-01-06'), 9, 9,6,9,3]//,
                        //]);
                        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
                        chart.draw(data, options);
                    })
                    .fail(function (xhr) {
                        $('#messages').html(xhr.responseText);
                    });

    }
</asset:script>