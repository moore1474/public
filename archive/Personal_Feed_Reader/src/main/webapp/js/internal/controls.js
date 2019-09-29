function setupControls(){
	
	//Sort and reload page
	$('#sort_combo').change(function(event){
		applySort();
		loadLinks();
	});
	
	//Filter and reload page
	$('.filter_combo').change(function(event){
		applyFilters();
		applySort();
		loadLinks();
	});
	
	//Update Links per page
	$('#links_per_page_combo').change(function(event){
		loadLinks();
	});
	
	//Action Combox Box - Add Link, Remove Visited, Refresh
	$('#action_combo_box').change(function(event){
		var selected = $(event.target).find(':selected').text().replace('\u25BC', '');
		if(selected=='Refresh Links'){
			$.ajax({
				dataType : "json",
				url : 'links',
				success : loadLinks
			});
		} else if(selected=='Add Link'){
			displayAddLinkDialog()
		} else if(selected=='Manage Feeds'){
			displayManageFeedsDialog()
		} else if(selected == 'Delete all on page'){
			var links = $('.link');
			links = links.slice(1, links.length);//Do not Include template
			links.each(function(i, link){
				link.remove();
				linkUpdateAjax(link, 'grouping','Deleted', function(){}, false);
			});
			loadLinks();
		}
		$(event.target).val('perm');
	});
}


function updateLinkCount(now){
	$('#links_now').text(now);
}