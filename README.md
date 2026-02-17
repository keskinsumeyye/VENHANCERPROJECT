#  Hepsiburada Otomasyon Projesi

Bu proje, Hepsiburada üzerinde ürün arama, filtreleme ve sepete ekleme süreçlerini test eden bir otomasyon çalışmasıdır.

##  Kullanılan Teknolojiler
* **Dil:** Java 21
* **Otomasyon:** Selenium WebDriver
* **Framework:** Cucumber (BDD)
* **Yönetim:** Maven

##  Çalıştırma Talimatları
* **IDE Kullanımı:** Projeyi **IntelliJ IDEA** veya **Eclipse** ile açın.
* **Bağımlılıklar:** Maven bağımlılıklarının (`pom.xml`) yüklenmesini bekleyin.
* **Çalıştırma:** `src/test/java` altındaki **Runner** sınıfına sağ tıklayıp "Run" yapın veya doğrudan `.feature` dosyası üzerinden testi başlatın.

##  Uygulama Notları & Teknik Çözümler

### 1. Dinamik Filtreleme ve StaleElement Hatası
Sayfa yenilenmelerinde oluşan `StaleElementReferenceException` hataları, elementlerin her işlem öncesinde **index bazlı** olarak taze referansla bulunmasıyla (**re-finding**) çözülmüştür.

### 2. Sekme Yönetimi (Switch Tab)
Ürün tıklandığında açılan yeni sekme için `switchToNewTab()` fonksiyonu kullanılmıştır.

### 3. Veri Doğrulama ve Temizlik
Karşılaştırmalarda hata payını sıfıra indirmek için ürün isimleri üzerinde `trim()` ve `toLowerCase()` metodları kullanılarak veri temizliği yapılmıştır.
