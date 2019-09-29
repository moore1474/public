function createPageNav() {
	// Add nav
	var template = $('#page_nav_template').clone();
	template.attr('id', 'page_nav');
	template.appendTo('#root');

	// Make prev and next functional
	template.find('.next_button').click(function(event) {
		page_number = page_number + 1;
		loadLinks();
	});
	template.find('.prev_button').click(function(event) {
		page_number = page_number - 1;
		loadLinks();
	});

	// Hide prev if on page 1
	if (page_number == 1) {
		template.find('.prev_button').hide();
	}
	// Hide next if on last page
	template.find('#num_of_pages').text(num_of_pages);
	if (page_number == num_of_pages) {
		template.find('.next_button').hide();
	}

	// Set Current page
	template.find('.page_num_combo_display').text(page_number + '\u25BC');

	// Add options for each page number
	$('.page_num_combo').find('option').not('.page_num_combo_display').remove();
	for (i = 1; i < num_of_pages + 1; i++) {
		$('.page_num_combo').append('<option>' + i + '</option>')
	}

	// Set on Page change
	$('.page_num_combo').change(function(event) {
		page_number = Number($(event.target).find(':selected').text());
		loadLinks();
	});
}

function getPageOfLinks(pageNum) {
	var linksPerPage = Number($('#links_per_page_combo').find(':selected')
			.text().replace('\u25BC', ''));

	// Calculate number of pages
	num_of_pages = Math.floor(filteredAndSortedKeys.length / linksPerPage);
	num_of_pages = filteredAndSortedKeys.length % linksPerPage == 0 ? num_of_pages
			: num_of_pages + 1;

	// Go to last page if current page > max page
	if (page_number > num_of_pages && num_of_pages != 0) {
		page_number = num_of_pages;
	}

	var startIndx = (pageNum - 1) * linksPerPage;
	var endIndx = startIndx + linksPerPage;
	endIndx >= filteredAndSortedKeys.length ? filteredAndSortedKeys.length - 1
			: endIndx;
	return filteredAndSortedKeys.slice(startIndx, endIndx);
}