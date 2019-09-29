package com.m1474.pfr.model.feed;

import java.util.Date;
import com.google.gson.annotations.SerializedName;
import com.m1474.pfr.model.Grouping;
import com.m1474.pfr.model.Link;
import com.m1474.pfr.model.LinkType;

public enum EffectOperator {

	@SerializedName(value = "Add")
	ADD, @SerializedName(value = "Subtract")
	SUBTRACT, @SerializedName(value = "Multiply")
	MULTIPLY, @SerializedName(value = "Divide")
	DIVIDE, @SerializedName(value = "Move To")
	MOVE_TO, @SerializedName(value = "Do Not Add")
	DO_NOT_ADD, @SerializedName(value = "Delete")
	DELETE, @SerializedName(value = "Permanently Delete")
	PERMANENTLY_DELETE;

	public void applyEffect(Link link, FeedRule feedRule) {
		switch (this) {
		case ADD:
			break;
		case SUBTRACT:
			break;
		case MULTIPLY:
			break;
		case DIVIDE:
			break;
		case MOVE_TO:
			String changeVal = feedRule.getChangeValue();
			LinkType linkType = LinkType.getValue(changeVal);
			if (linkType != null) {
				link.setType(linkType);
			} else {
				Grouping grouping = Grouping.getValue(changeVal);
				if(grouping!=null){
					link.setGrouping(grouping);
				}
			}
			break;
		case DELETE:
			if(link.getGrouping().equals(Grouping.CURRENT)){
				link.setGrouping(Grouping.DELETED);
				link.setDateDeleted(new Date());
			}
			break;
		case PERMANENTLY_DELETE:
			if(link != null & link.getGrouping() != null & Grouping.DELETED.equals(link.getGrouping())){
				link.setGrouping(Grouping.PERMANENTLY_DELETED);				
			};
			break;
		}
	}
}