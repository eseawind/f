(function($){
jQuery.fn.startMask = function(options) {
    
    var indicatorID;
    var settings = {
       	'addClass': '',
	'beforeShow': '', 
       	'afterShow': '',
       	'hPos': 'center', 
	'vPos': 'center',
       	'indicatorZIndex' : 5001, 
       	'overlayZIndex': 5000, 
	'parent': '',
       	'marginTop': 0,
       	'marginLeft': 0,
	'overlayWidth': null,
       	'overlayHeight': null
    };

    jQuery.extend(settings, options);
    
    var loadingDiv = jQuery('<div style="background-color:#fff;border:1px solid #ddd"></div>');
    var overlayDiv = jQuery('<div></div>');

    //
    // Set up ID and classes
    //
    if ( settings.indicatorID ) {
	indicatorID = settings.indicatorID;
    }
    else {
	indicatorID = jQuery(this).attr('id');
    }

    //jQuery(this).resize(function(e) {
    //    alert("Change event");
    //});

    jQuery(loadingDiv).attr('id', 'loading-indicator-' + indicatorID );
	loadingDiv.css('height','45px');
	loadingDiv.css('min-width','80px');
	/*if ( settings.addClass ){
	jQuery(loadingDiv).addClass(settings.addClass);
    }*/
    
    //
    // Create the overlay
    //
    jQuery(overlayDiv).css('display', 'none');
    
    // Append to body, otherwise position() doesn't work on Webkit-based browsers
    jQuery(document.body).append(overlayDiv);
    
    //
    // Set overlay classes
    //
    jQuery(overlayDiv).attr('id', 'loading-indicator-' + indicatorID + '-overlay');
    overlayDiv.css('background-color','#fff');
	overlayDiv.css('opacity','0.6');
	overlayDiv.css('filter','alpha(opacity = 60)');
    
    /*if ( settings.addClass ){
	jQuery(overlayDiv).addClass(settings.addClass + '-overlay');
    }*/
    
    //
    // Set overlay position
    //
    
    var overlay_width;
    var overlay_height;
    
    var border_top_width = jQuery(this).css('border-top-width');
    var border_left_width = jQuery(this).css('border-left-width');
    
    //
    // IE will return values like 'medium' as the default border, 
    // but we need a number
    //
    border_top_width = isNaN(parseInt(border_top_width)) ? 0 : border_top_width;
    border_left_width = isNaN(parseInt(border_left_width)) ? 0 : border_left_width;

    var overlay_left_pos = jQuery(this).offset().left + parseInt(border_left_width);// +  $(document.body).css( "border-left" );
    var overlay_top_pos = jQuery(this).offset().top + parseInt(border_top_width);
    
    if ( settings.overlayWidth !== null ) {
	overlay_width = settings.overlayWidth;
    }
    else {
	overlay_width = parseInt(jQuery(this).width()) + parseInt(jQuery(this).css('padding-right')) + parseInt(jQuery(this).css('padding-left'));
    }

    if ( settings.overlayHeight !== null ) {
	overlay_height = settings.overlayWidth;
    }
    else {
	overlay_height = parseInt(jQuery(this).height()) + parseInt(jQuery(this).css('padding-top')) + parseInt(jQuery(this).css('padding-bottom'));
    }


    jQuery(overlayDiv).css('width', overlay_width.toString() + 'px');
    jQuery(overlayDiv).css('height', overlay_height.toString() + 'px');

    jQuery(overlayDiv).css('left', overlay_left_pos.toString() + 'px');
    jQuery(overlayDiv).css('position', 'absolute');

    jQuery(overlayDiv).css('top', overlay_top_pos.toString() + 'px' );
    jQuery(overlayDiv).css('z-index', settings.overlayZIndex);

    //
    // Set any custom overlay CSS		
    //
    if ( settings.overlayCSS ) {
       	jQuery(overlayDiv).css ( settings.overlayCSS );
    }


    //
    // We have to append the element to the body first
    // or .width() won't work in Webkit-based browsers (e.g. Chrome, Safari)
    //
    jQuery(loadingDiv).css('display', 'none');
    jQuery(document.body).append(loadingDiv);
    
    jQuery(loadingDiv).css('position', 'absolute');
    jQuery(loadingDiv).css('z-index', settings.indicatorZIndex);

    //
    // Set top margin
    //

    var indicatorTop = overlay_top_pos;
    
    if ( settings.marginTop ) {
	indicatorTop += parseInt(settings.marginTop);
    }
    
    var indicatorLeft = overlay_left_pos;
    
    if ( settings.marginLeft ) {
	indicatorLeft += parseInt(settings.marginTop);
    }
    
    
    //
    // set horizontal position
    //
    if ( settings.hPos.toString().toLowerCase() == 'center' ) {
	jQuery(loadingDiv).css('left', (indicatorLeft + ((jQuery(overlayDiv).width() - parseInt(jQuery(loadingDiv).width())) / 2)).toString()  + 'px');
    }
    else if ( settings.hPos.toString().toLowerCase() == 'left' ) {
	jQuery(loadingDiv).css('left', (indicatorLeft + parseInt(jQuery(overlayDiv).css('margin-left'))).toString() + 'px');
    }
    else if ( settings.hPos.toString().toLowerCase() == 'right' ) {
	jQuery(loadingDiv).css('left', (indicatorLeft + (jQuery(overlayDiv).width() - parseInt(jQuery(loadingDiv).width()))).toString()  + 'px');
    }
    else {
	jQuery(loadingDiv).css('left', (indicatorLeft + parseInt(settings.hPos)).toString() + 'px');
    }		

    //
    // set vertical position
    //
    if ( settings.vPos.toString().toLowerCase() == 'center' ) {
	jQuery(loadingDiv).css('top', (indicatorTop + ((jQuery(overlayDiv).height() - parseInt(jQuery(loadingDiv).height())) / 2)).toString()  + 'px');
    }
    else if ( settings.vPos.toString().toLowerCase() == 'top' ) {
	jQuery(loadingDiv).css('top', indicatorTop.toString() + 'px');
    }
    else if ( settings.vPos.toString().toLowerCase() == 'bottom' ) {
	jQuery(loadingDiv).css('top', (indicatorTop + (jQuery(overlayDiv).height() - parseInt(jQuery(loadingDiv).height()))).toString()  + 'px');
    }
    else {
	jQuery(loadingDiv).css('top', (indicatorTop + parseInt(settings.vPos)).toString() + 'px' );
    }		


    
    
    //
    // Set any custom css for loading indicator
    //
    if ( settings.css ) {
       	jQuery(loadingDiv).css ( settings.css );
    }

    
    //
    // Set up callback options
    //
    var callback_options = 
	{
	    'overlay': overlayDiv,
	    'indicator': loadingDiv,
	    'element': this
	};
    
    //
    // beforeShow callback
    //
    if ( typeof(settings.beforeShow) == 'function' ) {
	settings.beforeShow( callback_options );
    }
    
    //
    // Show the overlay
    //
    jQuery(overlayDiv).show();
    
    //
    // Show the loading indicator
    //
    jQuery(loadingDiv).show();
	loadingDiv.append('<img src="data:image/gif;base64,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" />');
    jQuery(loadingDiv).append('<span style="line-height:300%;padding-left:10px;padding-right:10px">'+((options&&options.maskTip)||'努力加载中。。。')+'</span>');

    //
    // afterShow callback
    //
    if ( typeof(settings.afterShow) == 'function' ) {
	settings.afterShow( callback_options );
    }

    return this;
};


jQuery.fn.closeMask = function(options) {
    
    
    var settings = {};
    
    jQuery.extend(settings, options);

    if ( settings.indicatorID ) {
	indicatorID = settings.indicatorID;
    }
    else {
	indicatorID = jQuery(this).attr('id');
    }
    
    jQuery(document.body).find('#loading-indicator-' + indicatorID ).remove();
    jQuery(document.body).find('#loading-indicator-' + indicatorID + '-overlay' ).remove();
    
    return this;
};
})(jQuery);

