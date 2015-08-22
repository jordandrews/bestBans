(function($, undefined){
    $.widget('triton.filldown', {

        options: {},

        _create: function() {
            var self = this;
            // skip submit buttons and hidden fields
            // skip the date picker offset for now, not sure how to handle that properly
            if (self.element.is(':not(:input),:submit,input[type=hidden],.datePickerOffset,.money-currency')) {
                return false;
            }
            self.row = self.element.closest('tr');
            if(self.row.length === 0) {
                return false;
            }
            var tdElement = self.element.closest('td');
            self.tdIndex = self.row.children('td').index(tdElement);
            self.fillButton = $('<img>').attr({
                src: TF.contextPath+'/assets/icons/arrow_down.png',
                class: 'filldown-button',
                alt: 'Fill',
                title: 'Fill down'
            }).on('click', function() {
                if( self.options.disabled === true || self._trigger('onfill') === false) {
                    return;
                }
                self.fill();
            });
            self.element.addClass('filldown');

            var innerDiv = tdElement.find('div');
            if (innerDiv.length == 1) {
                innerDiv.append(self.fillButton);
            }
            else {
                tdElement.append(self.fillButton);
            }
        },

        fill: function() {
            var value = this.element.val();
            var params = {source: this.element};
            this.row.nextAll().find('td:eq(' + this.tdIndex + ') :input.filldown:visible').each(function() {
                if( !$(this).prop("readonly") && !$(this).prop("disabled") ) {
                    // By default, we will allow fill and use the original value. The prefill trigger
                    // can disable fill completely for an input or optionally change the fill value
                    params.allowFill = true;
                    params.value = value;
                    $(this).trigger("prefill", params);
                    if (params.allowFill) {
                        $(this).val(params.value).trigger("fill");
                    }
                }
            });
        },

        _setOption: function(key, value) {
            switch( key ) {}
            $.Widget.prototype._setOption.apply(this, arguments);
        },

        destroy: function() {
            this.fillButton.remove();
            $.Widget.prototype.destroy.call(this);
        }
    });
})(jQuery, undefined);

