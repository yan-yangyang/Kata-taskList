### Refactor 項目
- Controller新增try catch block避免因use case failure導致app失效。  
- 將use case的錯誤資訊顯示在console。
- 在add 不合法的物件(不是project or task)時拋異常。
- 使add task不破壞project的封裝性。
- 使project和task往外傳的時候變成immutable.