package com.ticket.entity;

public class Zone {
	private String zone_id, zone_name, city_id;

	public Zone() {
	}

	public Zone(String zone_id, String zone_name, String city_id) {
		this.zone_id = zone_id;
		this.zone_name = zone_name;
		this.city_id = city_id;
	}

	public String getZone_id() {
		return zone_id;
	}

	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
}
