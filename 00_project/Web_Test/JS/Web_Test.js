// #region jQuery Methods
$(function() {
    $('#menu').click(function (e) { 
        const maxNavWidth = 5;
        if($('#nav').width() < 0.01) {

            $('#nav').css('width', `${maxNavWidth}rem`);
            $('#nav').css('visibility', 'visible');
            console.log(($('#nav').width()));

        } else if($('#nav').width() > 0.01) {

            $('#nav').css('width', `0`);
            $('#nav').css('visibility', 'visible');
            console.log(($('#nav').width()));
        }
    });
});
// #endregion







































