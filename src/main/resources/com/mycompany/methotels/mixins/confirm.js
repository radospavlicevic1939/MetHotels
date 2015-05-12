/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function ($) {
    T5.extendInitializers(function () {
        function confirmation(spec) {
            $("#" + spec.id).bind("click", function (e) {
                if (!confirm(spec.message))
                    e.preventDefault();
            });
        }
        return {confirmation: confirmation};
    });
})(jQuery);
