package me.cooperka.cafalert;

import android.graphics.Color;

public class Caf
{
	private String name = "", hours = "", code = "";
	private int color = Color.WHITE;

	public void setStuff(String Name, String Code, String Hours, int Color) {
		this.name = Name;
		this.code = Code;
		this.hours = Hours;
		this.color = Color;
	}

	public void setStuff(String Name, String Hours, int Color) {
		this.name = Name;
		this.hours = Hours;
		this.color = Color;
	}

	public void setStuff(String Name, int Color) {
		this.name = Name;
		this.color = Color;
	}

	public void setStuff(String Name) {
		this.name = Name;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String Hours) {
		this.hours = Hours;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String Code) {
		this.code = Code;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
