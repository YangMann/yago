// Gumby is ready to go
Gumby.ready(function() {
	Gumby.log('Gumby is ready to go...', Gumby.dump());

	// placeholder polyfil
	if(Gumby.isOldie || Gumby.$dom.find('html').hasClass('ie9')) {
		$('input, textarea').placeholder();
	}

	// skip link and toggle on one element
	// when the skip link completes, trigger the switch
	$('#skip-switch').on('gumby.onComplete', function() {
		$(this).trigger('gumby.trigger');
	});

// Oldie document loaded
}).oldie(function() {
	Gumby.warn("This is an oldie browser...");

// Touch devices loaded
}).touch(function() {
	Gumby.log("This is a touch enabled device...");
});

$(document).ready(function() {
    $(document).find("#button-submit").on(
        "click",
        function(e) {
            e.preventDefault();
            var queryContent = $(document).find("input[name='query-content']").val();
            var queryType = $(document).find("select[name='query-type']").val();
            $(document).find("#floatingBarsG").removeClass("hide");
            $.post("query", {
                    "query-content": queryContent,
                    "query-type": queryType
                },
                function(result) {
                    $(document).find("section#result").html(result);
                    $(document).find("#floatingBarsG").addClass("hide");
                }
            );
        }
    );
});
