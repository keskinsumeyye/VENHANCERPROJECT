#  Hepsiburada Otomasyon Projesi

Bu proje, Hepsiburada üzerinde ürün arama, filtreleme ve sepete ekleme süreçlerini test eden, sürdürülebilir bir otomasyon çalışmasıdır.

##  Kullanılan Teknolojiler & Mimari
* **Dil:** Java 21
* **Otomasyon:** Selenium WebDriver
* **Test Yönetimi:** TestNG & Cucumber (BDD)
* **Mimari Yapı:** Page Object Model (POM)
* **Bağımlılık Yönetimi:** Maven

##  Proje Mimarisi (Page Object Model)
Proje, kodun tekrar kullanılabilirliğini artırmak ve bakımı kolaylaştırmak için **POM** yapısı üzerine kurulmuştur:
* **BasePage:** Tüm sayfa sınıflarının türediği, ortak driver metodlarını (click, sendKeys, switchTab) içeren ana sınıftır.
* **Pages:** Her web sayfası (Home, Listing, Product, Cart) kendine ait bir Java sınıfına sahiptir.
* **StepDefinitions:** Gherkin dilindeki senaryo adımlarının teknik karşılıklarını barındırır.

##  Çalıştırma Talimatları
* **IDE Kullanımı:** Projeyi **IntelliJ IDEA** veya **Eclipse** ile açın.
* **Bağımlılıklar:** Maven bağımlılıklarının (`pom.xml`) yüklenmesini bekleyin.
* **Çalıştırma:** `src/test/java` altındaki **Runner** sınıfına sağ tıklayıp "Run" yapın veya doğrudan `.feature` dosyası üzerinden testi başlatın.

##  Teknik Çözümler & Uygulama Notları

### 1. Dinamik Filtreleme ve StaleElement Çözümü
Sayfa yenilenmelerinde oluşan `StaleElementReferenceException` hataları, elementlerin her işlem öncesinde **index bazlı** olarak taze referansla bulunmasıyla (**re-finding**) çözülmüştür.

### 2. Sekme Yönetimi (Switch Tab)
Ürün tıklandığında açılan yeni sekme için `switchToNewTab()` fonksiyonu geliştirilerek Selenium odağının yeni sayfaya geçmesi sağlanmıştır.

### 3. Veri Doğrulama ve Normalizasyon
Karşılaştırmalarda hata payını sıfıra indirmek için:
* `trim()` ile boşluklar temizlenmiş.
* `toLowerCase()` ile büyük/küçük harf duyarlılığı ortadan kaldırılmıştır.
* Veri, sayfalar arası **static** değişkenler aracılığıyla güvenli bir şekilde taşınmıştır.
