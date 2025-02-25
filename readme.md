# Shopping Cart Microservice  

## 📌 Assignment Overview  
The goal of this project is to build a small **retail system** that allows customers to purchase products via digital channels. 
The system follows a **microservice architecture**, and this component is responsible for managing the **Shopping Cart**.  

## 🎯 Features  
This Shopping Cart microservice should be able to:  
- ✅ Provide the current content of a cart for a particular customer  
- ✅ Add a particular item to a cart  
- ✅ Remove a particular item from a cart  
- ✅ Evict a cart  
- ✅ Provide statistics on how many offers of a particular ID and action were sold in a specific period  


### **📦 Project Modules**  

#### **1️⃣ Controller Layer (`controller/`)**
- **CartController.java** → Handles HTTP requests for cart operations.  

#### **2️⃣ Model Layer (`model/`)**
- **ActionType.java** → Defines action types (`ADD`, `MODIFY`, `DELETE`).  
- **Item.java** → Represents an item in the cart.  
- **OneTimePrice.java / RecurringPrice.java** → Price models for different price types.  
- **Price.java** → Price model for items. 
- **PriceType.java** → PriceType model (`ONE_TIME`, `RECURRING`) . 
- **StatisticsResponse.java** → Model for statistical data.  

#### **3️⃣ Repository Layer (`repository/`)**
- **ItemRepository.java** → MongoDB repository for managing cart items.  

#### **4️⃣ Service Layer (`service/`)**
- **ItemService.java** → Handles business logic for shopping cart operations.  

#### **5️⃣ Application Entry Point**
- **ShoppingCartApplication.java** → The main Spring Boot application.  


## Installation & Running the Project  

1. Clone the repository:  
```bash
git clone git@github.com:ivan1602/shoppingcart2.git
cd shoppingcart2
```
2. Install dependencies:
```bash 
./gradlew clean build
```
3. Run the project:
```bash
./gradlew bootRun
```


## 📡 API Endpoints

**Cart Operations**

Get all items in a cart
```
GET /carts/{customerId}/items
```
Add an item to a cart
```
POST /carts/{customerId}/items
```
Remove a specific item from a cart
```
DELETE /carts/remove?customerId={customerId}&identifier={itemId}
```
Evict (clear) a cart
```
DELETE /carts/evict
```

**Statistics**

Get full statistics for sold offers
```
GET /carts/fullstatistics?identifier={identifier}&startDate={YYYY-MM-DDTHH:MM:SS}&endDate={YYYY-MM-DDTHH:MM:SS}
```

### 📌 Example Requests

**1️⃣ Get Cart Items**
```
GET /carts/12345/items
```
Response:
```
{
   "identifier": "item1",
    "action": "ADD",
    "prices": [    
        {
            "amount": "110.10",
            "type": "ONE_TIME"                     
            
        },
        {
            "amount": "120.11",
            "type": "RECURRING",         
            "numberOfRecurrences": "12"
        }      

    ],
    "customerId": "customer123"
}
```
**2️⃣ Add an Item to Cart**
```
POST /carts/12345/items
```

```
{
   "identifier": "item1",
    "action": "ADD",
    "prices": [    
        {
            "amount": "1110",
            "type": "ONE_TIME"                     
            
        },
        {
            "amount": "1310",
            "type": "RECURRING",         
            "numberOfRecurrences": "15"
        }      

    ],
    "customerId": "customer123"
}
```
**3️⃣ Remove an Item**
```
DELETE /carts/remove?customerId=12345&identifier=item-002
```
**4️⃣ Evict a Cart**
```
DELETE /carts/evict
```
**5️⃣ Get Statistics**
```
GET /carts/fullstatistics?identifier=product-001&startDate=2024-01-01T00:00:00&endDate=2024-12-31T23:59:59
```