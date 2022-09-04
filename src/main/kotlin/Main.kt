import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val token = "c2yrPBm4RceG-Gk8S8t63d:APA91bGWtnbsvksNwENJwLFrHA4wc5BxyfraA0ViMcxijW56qD7V5QfCzP2D04l9udC0hdN6FS83qmrzKmpZ1ZVFsngaSPTTc9MGqXTI16qo5HUy-SIb7VmH5zi0f78LZb5-HoTvVzuq"

    // Сообщение из лекции
    val message = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
            "userId": 1,
            "userName": "Vasiliy",
            "postId": 2,
            "postAuthor": "Netology"
            }""".trimMargin()
        )
        .setToken(token)
        .build()

    // Сообщение для нового поста
    val message2 = Message.builder()
        .putData("action", "NEW_POST")
        .putData("content", """{
            "userId": 2,
            "userName": "Ivan",
            "postId": 3,
            "postAuthor": "Netology",
            "text": "Новая Нетология — это 4 уровня. Получить актуальную профессию с нуля: базовые курсы для изучения новой сферы или профессии — с практикой и помощью в трудоустройстве. Вырасти в карьере: курсы продвинутого уровня для тех, кто хочет усилить позиции в текущей профессии, увеличить доход, изучить новые инструменты. Развить лидерские навыки: курсы для руководителей, чтобы найти точки роста в управлении командой и бизнесом." 
            }""".trimMargin())
        .setToken(token)
        .build()

    // Сообщение для проверки реакции на не существующий action
    val message3 = Message.builder()
        .putData("action", "REPOST")
        .putData("content", """{
            "userId": 2,
            "userName": "Ivan",
            "postId": 3,
            "postAuthor": "Netology",
            "text": "Новая" 
            }""".trimMargin())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
    FirebaseMessaging.getInstance().send(message2)
    FirebaseMessaging.getInstance().send(message3)
}