package com.m1474.pfr.model.feed;

public class FeedRule {

	private Field field;
	private Operator operator;
	private String value;
	private String fieldToChange;
	private EffectOperator effectOperator;
	private String changeValue;
	
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFieldToChange() {
		return fieldToChange;
	}
	public void setFieldToChange(String fieldToChange) {
		this.fieldToChange = fieldToChange;
	}
	public EffectOperator getEffectOperator() {
		return effectOperator;
	}
	public void setEffectOperator(EffectOperator effectOperator) {
		this.effectOperator = effectOperator;
	}
	public String getChangeValue() {
		return changeValue;
	}
	public void setChangeValue(String changeValue) {
		this.changeValue = changeValue;
	}
	
}