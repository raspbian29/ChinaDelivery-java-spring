package com.chinabox.delivery.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "package_request")
public class PackageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String trackCode;
    @Column()
    private String receivedInChinaBy;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime chinaWarehouseArrivedDate;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime chinaWarehouseSentDate;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime localWarehouseArrivedDate;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime packageRequestClose;
    @Column
    private ItemType itemType;
    @Column
    private BigDecimal price;
    @Column
    private Currency currency;
    @Column
    private Double amount;
    @Column
    private String shopName;
    @Column
    private String itemNotes;
    @Column
    private Boolean itemPhoto;
    @Column
    private Boolean itemInsurance;
    @Column
    private Boolean itemCheck;
    @Column
    private Boolean itemRepack;
    @Column
    private Boolean itemSplit;
    @ManyToOne
    private User user;
    @ManyToOne
    private User operator;
    @Column
    private Double weight;
    @Column
    private Double height;
    @Column
    private Double width;
    @Column
    private Double length;
    @Column
    BigDecimal deliveryPrice;

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    @Column
    private Boolean isPaid;

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getChinaWarehouseArrivedDate() {
        return chinaWarehouseArrivedDate;
    }

    public void setChinaWarehouseArrivedDate(LocalDateTime chinaWarehouseArrivedDate) {
        this.chinaWarehouseArrivedDate = chinaWarehouseArrivedDate;
    }

    public LocalDateTime getChinaWarehouseSentDate() {
        return chinaWarehouseSentDate;
    }

    public void setChinaWarehouseSentDate(LocalDateTime chinaWarehouseSentDate) {
        this.chinaWarehouseSentDate = chinaWarehouseSentDate;
    }

    public LocalDateTime getLocalWarehouseArrivedDate() {
        return localWarehouseArrivedDate;
    }

    public void setLocalWarehouseArrivedDate(LocalDateTime localWarehouseArrivedDate) {
        this.localWarehouseArrivedDate = localWarehouseArrivedDate;
    }

    public LocalDateTime getPackageRequestClose() {
        return packageRequestClose;
    }

    public void setPackageRequestClose(LocalDateTime packageRequestClose) {
        this.packageRequestClose = packageRequestClose;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String itemNotes) {
        this.itemNotes = itemNotes;
    }

    public Boolean getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(Boolean itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public Boolean getItemInsurance() {
        return itemInsurance;
    }

    public void setItemInsurance(Boolean itemInsurance) {
        this.itemInsurance = itemInsurance;
    }

    public Boolean getItemCheck() {
        return itemCheck;
    }

    public void setItemCheck(Boolean itemCheck) {
        this.itemCheck = itemCheck;
    }

    public Boolean getItemRepack() {
        return itemRepack;
    }

    public void setItemRepack(Boolean itemRepack) {
        this.itemRepack = itemRepack;
    }

    public Boolean getItemSplit() {
        return itemSplit;
    }

    public void setItemSplit(Boolean itemSplit) {
        this.itemSplit = itemSplit;
    }

    public String getReceivedInChinaBy() {
        return receivedInChinaBy;
    }

    public void setReceivedInChinaBy(String receivedInChinaBy) {
        this.receivedInChinaBy = receivedInChinaBy;
    }

    @Override
    public String toString() {
        return "PackageRequest{" +
                "id=" + id +
                ", trackCode='" + trackCode + '\'' +
                ", receivedInChinaBy='" + receivedInChinaBy + '\'' +
                ", createdDate=" + createdDate +
                ", chinaWarehouseArrivedDate=" + chinaWarehouseArrivedDate +
                ", chinaWarehouseSentDate=" + chinaWarehouseSentDate +
                ", localWarehouseArrivedDate=" + localWarehouseArrivedDate +
                ", packageRequestClose=" + packageRequestClose +
                ", itemType=" + itemType +
                ", price=" + price +
                ", currency=" + currency +
                ", amount=" + amount +
                ", shopName='" + shopName + '\'' +
                ", itemNotes='" + itemNotes + '\'' +
                ", itemPhoto=" + itemPhoto +
                ", itemInsurance=" + itemInsurance +
                ", itemCheck=" + itemCheck +
                ", itemRepack=" + itemRepack +
                ", itemSplit=" + itemSplit +
                ", user=" + user +
                '}';
    }
}
