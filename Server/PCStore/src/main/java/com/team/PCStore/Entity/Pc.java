package com.team.PCStore.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pc")
public class Pc {
    public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public Double getPcPrice() {
		return pcPrice;
	}

	public void setPcPrice(Double pcPrice) {
		this.pcPrice = pcPrice;
	}

	public String getPcBrand() {
		return pcBrand;
	}

	public void setPcBrand(String pcBrand) {
		this.pcBrand = pcBrand;
	}

	public Double getPcWeight() {
		return pcWeight;
	}

	public void setPcWeight(Double pcWeight) {
		this.pcWeight = pcWeight;
	}

	public String getPcGraCard() {
		return pcGraCard;
	}

	public void setPcGraCard(String pcGraCard) {
		this.pcGraCard = pcGraCard;
	}

	public String getPcResolution() {
		return pcResolution;
	}

	public void setPcResolution(String pcResolution) {
		this.pcResolution = pcResolution;
	}

	public String getPcType() {
		return pcType;
	}

	public void setPcType(String pcType) {
		this.pcType = pcType;
	}

	public String getPcSystem() {
		return pcSystem;
	}

	public void setPcSystem(String pcSystem) {
		this.pcSystem = pcSystem;
	}

	public String getPcThickness() {
		return pcThickness;
	}

	public void setPcThickness(String pcThickness) {
		this.pcThickness = pcThickness;
	}

	public String getPcCPU() {
		return pcCPU;
	}

	public void setPcCPU(String pcCPU) {
		this.pcCPU = pcCPU;
	}

	public String getPcMemory() {
		return pcMemory;
	}

	public void setPcMemory(String pcMemory) {
		this.pcMemory = pcMemory;
	}

	public String getPcSSD() {
		return pcSSD;
	}

	public void setPcSSD(String pcSSD) {
		this.pcSSD = pcSSD;
	}

	public String getPcHDD() {
		return pcHDD;
	}

	public void setPcHDD(String pcHDD) {
		this.pcHDD = pcHDD;
	}

	public String getPcPlaceOfOrigin() {
		return pcPlaceOfOrigin;
	}

	public void setPcPlaceOfOrigin(String pcPlaceOfOrigin) {
		this.pcPlaceOfOrigin = pcPlaceOfOrigin;
	}

	public String getPcColor() {
		return pcColor;
	}

	public void setPcColor(String pcColor) {
		this.pcColor = pcColor;
	}

	public String getPcPicture() {
		return pcPicture;
	}

	public void setPcPicture(String pcPicture) {
		this.pcPicture = pcPicture;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

    @Id
    @Column(name = "pcId")
    private Integer pcId;

    @Column(name = "pcName")
    private String pcName;

    @Column(name = "pcPrice")
    private Double pcPrice;

    @Column(name = "pcBrand")
    private String pcBrand;

    @Column(name = "pcWeight")
    private Double pcWeight;
    
    @Column(name = "pcGraphicsCard")
    private String pcGraCard;
    
    @Column(name = "pcResolution")
    private String pcResolution;
    
    @Column(name = "pcType")
    private String pcType;
    
    @Column(name = "pcSystem")
    private String pcSystem;
    
    @Column(name = "pcThickness")
    private String pcThickness;
    
    @Column(name = "pcCpu")
    private String pcCPU;
    
    @Column(name = "pcMemory")
    private String pcMemory;
    
    @Column(name = "pcSsd")
    private String pcSSD;
    
    @Column(name = "pcHdd")
    private String pcHDD;

    @Column(name = "pcPlaceOfOrigin")
    private String pcPlaceOfOrigin;
    
    @Column(name = "pcColor")
    private String pcColor;
    
    @Column(name = "pcPicture")
    private String pcPicture;

    @Column(name = "pcDetailinformation")
    private String pcDetail;

}
