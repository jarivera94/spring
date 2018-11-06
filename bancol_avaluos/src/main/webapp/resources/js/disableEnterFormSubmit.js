$('form').off('keypress.disableAutoSubmitOnEnter').on('keypress.disableAutoSubmitOnEnter', function(event) {
    if (event.which === $.ui.keyCode.ENTER && $(target).is(':input:not(textarea,:button,:submit,:reset)')) {
        event.preventDefault();
    }
});