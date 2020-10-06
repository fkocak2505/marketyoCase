//======== Case' de kullanılan Yapılar ve Kütüphaneler Hakkında

- MVVM Live Data

- RxJava3

- Gson

- Retrofit2

- NavigationController & Safeargs

- RecyclerView & CardView




//======== Case Ekranlar Hakkında

- Verilen case 2 Activity üzerinde çalışmaktadır. Bunlar
		
	- SplashActivity
	- MainActivity



=== SplashActivity

- İlk activity olan SplashActivity' de MotionLayout ile animasyonlu bir logo geçişi ayarlandı. 

- Case' de verildiği gibi 1 sn olarak delay süresi ayarlandı



=== MainAcitivity

- Case, performans ve ekran geçişleri düşünülerek fragment' lar üzerine kuruldu.

- MainActivity layout' unda bir fragment tanımlası yapıldı ve navigationController yapısı ile fragment' ler üzerine entegre edildi.

- Navigation Controller üzerinde yer alan fragment ayarlamaları "res->navigation->users.xml" içerisinde ayarlandı.

- Uygulama içerisinde 4 adet fragment yer almaktadır. Bunlar;
  - UserFragment
  - UserPostsFragment
  - PostDetailFragment
  - PostCommentsFragment


- UserFragment' dan UserPostsFragment' a geçiş yapılırken "userId" keyWord' u "@Query" ile parametre olarak geçildi

- PostDetailFragment' dan PostCommentsFragment' a geçiş yapılırken "postId" keyWord' u "@Query" ile parametre olarak geçildi


