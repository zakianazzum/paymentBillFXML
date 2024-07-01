package com.example.mitermexam_2130086_sec6;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MidtermVController
{
    @javafx.fxml.FXML
    private TextField perUnitProductCostTextField;
    @javafx.fxml.FXML
    private Label predefinedVatLabel;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Float> summaryUnitPriceTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Float> summaryTotalAmountTableColumn;
    @javafx.fxml.FXML
    private TableView<CartItem> cartDetailTableView;
    @javafx.fxml.FXML
    private Label showTotalVatAmountCriteriaLabel;
    @javafx.fxml.FXML
    private Label UnitPriceLabel;
    @javafx.fxml.FXML
    private TableColumn<CartItem, String> summaryProductTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Float> summaryVatAmountTableColumn;
    @javafx.fxml.FXML
    private ComboBox<Integer> vatToConsiderComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> productListCombobox;
    @javafx.fxml.FXML
    private ComboBox<Integer> quantityComboBox;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Integer> summaryQuantityTableColumn;
    @FXML
    private Label totalAmountShowLabel;

    private ArrayList<CartItem> cartList = new ArrayList<>();

    private String[] productNames = {"Arong Dairy Milk", "Butter", "Cheese", "Honey", "Oats",
                                            "PlayStation 5"};
    private float[] unitPrices = {50.0f, 45.0f, 65.0f, 75.0f, 80.0f, 30000.0f };
    private int[] vatRates = {1, 2, 4, 7, 3, 5 };



    @FXML
    public void initialize() {
        quantityComboBox.getItems().addAll(1,2, 3, 4, 5, 6, 7, 8, 9, 10);
        vatToConsiderComboBox.getItems().addAll(1,2, 3, 4, 5, 6, 7, 8, 9, 10);

        productListCombobox.getItems().addAll(productNames);
        productListCombobox.setOnAction(this::onProductSelected);

        summaryProductTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        summaryUnitPriceTableColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getUnitPrice()).asObject());
        summaryQuantityTableColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        summaryTotalAmountTableColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getTotalAmount()).asObject());
        summaryVatAmountTableColumn.setCellValueFactory(cell -> new SimpleFloatProperty(cell.getValue().getVatRate()).asObject());

    }

    @javafx.fxml.FXML
    public void showTotalAmountCriteriaButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void checkOutButton(ActionEvent actionEvent) {
        cartDetailTableView.getItems().clear();
        cartDetailTableView.getItems().addAll(cartList);
        float totalAmount = 0;
        for (CartItem item : cartList){
            totalAmount += item.getTotalAmount();
        }
        totalAmountShowLabel.setText(String.valueOf(totalAmount));
    }

    @javafx.fxml.FXML
    public void addProductCartButton(ActionEvent actionEvent) {
        String selectedProduct = productListCombobox.getValue();
        int index = findProductIndex(selectedProduct);
        int quantity = quantityComboBox.getValue();

        CartItem existingItem = null;
        for (CartItem item : cartList) {
            if (item.getProductName().equals(selectedProduct)) {
                existingItem = item;
                break;
            }
        }
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }
        else{
            CartItem newItem = new CartItem(selectedProduct,unitPrices[index],quantity,vatRates[index]);
            cartList.add(newItem);
        }
        // Clear all input boxes
        productListCombobox.setValue(null);
        UnitPriceLabel.setText("");
        predefinedVatLabel.setText("");
        quantityComboBox.setValue(null);

    }

    private int findProductIndex(String productName){
        for (int i = 0; i < productNames.length; i++){
            if (productNames[i].equals(productName)){
                return i;
            }
        }
        return -1;
    }

    @javafx.fxml.FXML
    public void onProductSelected(ActionEvent actionEvent) {
        String selectedProduct = productListCombobox.getValue();
        int index = findProductIndex(selectedProduct);
        if (index != -1) {
            UnitPriceLabel.setText(String.valueOf(unitPrices[index]));
            predefinedVatLabel.setText(String.valueOf(vatRates[index]));
        }
    }


}