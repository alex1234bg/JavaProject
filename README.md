Описание на проекта за магазин на Александър Иванов Петров:
Пакет:store.model
1.Product(абстрактен клас):
-Базов клас за всички продукти в магазина
- Полета:id,name,deliveryPrice,expirationDate,quantity,category
-Метод calculateSellingPrice()- изчислява продажната цена с надценка и евентуална отстъпка.
- Имплементира интерфейс Discountable
2.FoodProduct и NonFoodProduct:
-Подкласове на Product: единия за хранителни другия за нехранителни стоки.
- Използват се различни стойности за надценка спрямо вида на продукта.
3.Category(enum):
-Представлява категория на продукта:FOOD,NON_FOOD.

4. Cashier:
-Представлява касиерът със полета за id,name,monthlySalary след това се използва в бележката и се добавя към магазина.
5.Reciept:
-Представлява касовата бележка със полета за receiptNumber,cashier,timestamp,purchasedProducts,total използва вътрешен клас PurchaseInfo за да съхранява количество и цена на всеки продукт и накрая метод toString() , който отпечатва бележката във правилния формат.
6.Store:
-Това е основен клас, който съдържа във себе си списък от продукти, списък от касиер и списък от бележки. След това има полета за настройка на надценка и отстъпка и методи:addProduct(),addCashier(),addReceipt(),calculateExpenses(),getTotalRevenue() и calculateProfit(), които служат за добавяне на продукт,касиер и бележка, изчисляване на разходи, приходи и накрая общата печалба.
Пакет  store.service:
1.SaleServiceInterFace:
- Ингерфейс, който определя метода processSale().
2.SaleService:
-имплентира SaleServiceInterFace, след което обработва продажбата: проверява срок на годност, проверява наличност, изчислява цена с надценка и евентуална отстъпка и издава бележка. Накрая хвърля InsufficientQuantityException ако няма достатъчно наличност.
Пакет store.exceptions:
1.	InsufficientQuantityException:
-Персонализирано изключение, което се хвърля при опит за покупка на продукт с недостатъчно количество.Съобщението, което се изкарва съдържа коя стока липсва и колко още е нужно от нея.

Пакет store.util:
1.	ReceiptFileManager:
-Отговаря за запис и четене на бележки от/до файлове
- Метод saveReceiptAsText() – създава .txt файл
- Метод saveReceiptSerialized – създава  .ser файл
-Метод loadReceiptSerialized – зарежда .ser файл
-Метод readReceiptText – прочита .txt файл като текст

Тестови класове:
1.SaleServiceTest:
- Тества успешната продажба и изключение при недостатъчно количество.
2.StoreTest:
-Тества изчисление на разходи(заплати + доставки),изчисление на печалба и добавяне на бележка и приходи.
3.ReceiptFileManagerTest:
-Тества запис на .txt файл, сериализация и десериализация и четене на текстово съдържание.

Main.java:
Демонстрация на реална употреба на проекта:
-Създаването на магазин, продукти и касиер след това продажба и отпечатване на бележката, запис в текстов файл и сериализиран файл, изчисление на приходи,разходи и печалба, Накрая пример за хвърляне и обработка на изключение при недостиг на даден продукт.



