 
$("#seccode0").focus(function(){
            $("#tipAuto").cvtooltip({
                panel: "#tipArea",
                direction: 0,                
                close: false,
                width: 83,
                left: 35,
                top: 205,
                speed: 300,
                delay: 1000
            });
        });

$("#seccode0").click(function(){
	$("#tipAuto").cvtooltip({
        panel: "#tipArea",
        direction: 0,                
        close: false,
        width: 83,
        left: 35,
        top: 205,
        speed: 300,
        delay: 1000
    });
});