<div id="add_link_dialog" title="Add Link">
	  <strong  class='add_link_dialog_header'>Link Title</strong><br/>
      <input size="60" id="add_title">
      <br/><br/>
      
      <strong class='add_link_dialog_header'>URL</strong><br/>
      <input size="60" id="add_url">
      <br/><br/> 
      
      <strong class='add_link_dialog_header'>Grouping</strong> <select
			id="add_group" class="add_link_select">
			<option value='perm' class='combo_display' selected="true"
					style="display: none;">Current&#x25BC;</option>
			<option>Current</option>
			<option>Later</option>
			<option>Much Later</option>
			<option>One Day</option>
			<option>Favorited</option>
			<option>Software</option>
			<option>Career</option>
			<option>Study</option>
			<option>Game</option>
			<option>Deleted</option>
		</select>
		
		<strong class='add_link_dialog_header'>Type</strong> <select
			 id="add_type" class="add_link_select">
			 <option value='perm' class='combo_display' selected="true"
					style="display: none;">Text&#x25BC;</option>
			<option>Text</option>
			<option>Image</option>
			<option>Video</option>
			<option>Audio</option>
		</select>
		
		<br/><br/>
		
		<strong class='add_link_dialog_header'>Priority&nbsp;</strong>
      	<input size="3" id="add_priority">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      	
      	<strong class='add_link_dialog_header'>SFW Level&nbsp;</strong>
      	<input size="3" id="add_sfw">
      	
      	<br/><br/>
      	
      	<input id='add_link_submit' type='submit'/>
	</div>