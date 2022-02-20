<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../init.jsp" %>
<link href="http://fullcalendar.io/js/fullcalendar-2.2.5/fullcalendar.css" rel="stylesheet"></link>
<link href="http://fullcalendar.io/js/fullcalendar-2.2.5/fullcalendar.print.css"
      rel="stylesheet" media="print"></link>
<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"
        type="text/javascript"></script>
<script src="http://cdn.jsdelivr.net/webjars/jquery/3.4.1/jquery.min.js"
        type="text/javascript"></script>
<script src="http://fullcalendar.io/js/fullcalendar-2.2.5/fullcalendar.min.js"></script>

<portlet:resourceURL id="findState" var="findState"></portlet:resourceURL>
<style>
    body {
        margin: 40px 10px;
        padding: 0;
        font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
        font-size: 14px;
    }

    #calendar {
        max-width: 900px;
        margin: 0 auto;
    }
</style>
<body>

<div id='calendar'></div>
</body>
<script>
    $(document).ready(function () {
        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month'
            },
            contentHeight: 600,

            eventMouseover: function (calEvent, jsEvent) {
                var tooltip = '<div class="tooltipevent">' + calEvent.title + '</div>';
                $("body").append(tooltip);
                $(this).mouseover(function (e) {
                    $(this).css('z-index', 10000);
                    $('.tooltipevent').fadeIn('500');
                    $('.tooltipevent').fadeTo('10', 1.9);
                }).mousemove(function (e) {
                    $('.tooltipevent').css('top', e.pageY + 10);
                    $('.tooltipevent').css('left', e.pageX + 20);
                });
            },

            eventMouseout: function (calEvent, jsEvent) {
                $(this).css('z-index', 8);
                $('.tooltipevent').remove();
            },
            defaultDate: '2021-11-01',
            editable: true,
            selectable: true,
            selectHelper: true,
            eventLimit: true, // allow "more" link when too many events
            events: function (start, end, timezone, callback) {
                var events = [];
                $.ajax({
                    url: "${findState}" ,
                    type: 'get',
                    dataType: 'json',
                    success: function (data) {
                        for (var i = 0; i < data.length; i++) {
                            events.push({
                                start: data[i].start,
                                end: data[i].end,
                                title: data[i].title,
                                description: data[i].description
                            });
                        }
                    }
                });
                return events;
            }
        });
    });

</script>