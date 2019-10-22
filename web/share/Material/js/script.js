$(function () {

    var note = $('#note'),
            ts = new Date(2012, 0, 1),
            newYear = true;

    if ((new Date()) > ts) {
        ts =  2 * 60 * 60;
        newYear = false;
    }
    
    $('#countdown').countdown({
        timestamp: ts,
        hours: 1,
        minutes: 30,
        seconds: 0,
        callback: function (days, hours, minutes, seconds) {
        }
    });

});
