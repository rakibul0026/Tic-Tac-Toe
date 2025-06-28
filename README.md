# 🎮 Tic Tac Toe - Android App

A classic **Tic Tac Toe** game built with **Java** in **Android Studio**, integrated with **Firebase** for user authentication and leaderboard management.

## 🚀 Features

- 🔄 **Multiplayer Mode** – Play with a friend locally
- 🤖 **Single Player Mode** – Challenge the computer AI
- 📊 **Leaderboard** – Track top scores using Firebase Realtime Database
- 🙍‍♂️ **User Login/Profile** – Firebase Authentication integration
- 🎨 **Modern UI** – Clean Android Material design with `EdgeToEdge` layout
- 🔔 **Toast Notification** on exit

---

## 🛠️ Technologies Used

- **Java** – Primary language
- **Firebase** – Authentication & Realtime Database
- **Android Studio** – App development environment
- **XML** – UI layout design
## image
![image](https://github.com/user-attachments/assets/c31f13e4-5d5c-4044-a48b-95a160065035)
![image](https://github.com/user-attachments/assets/daa9f72c-34de-4dec-b941-ab61e7a76aa2)
![image](https://github.com/user-attachments/assets/7c2702a8-29cb-416a-a2da-1400281e9eea)
![image](https://github.com/user-attachments/assets/2660115a-6a52-4a19-8825-d85b964059a9)







## 📂 Project Structure
<pre> ``` com.example.tictactoe/ 
  ├── ActivityHome.java # Home screen with game mode selection
  ├── profile.java # Login/Profile screen using Firebase Authentication
  ├── playfriend.java # Local multiplayer mode (2 players on same device)
  ├── MainActivity.java # Single-player mode against AI (computer)
  ├── Leader_Broard.java # Displays leaderboard using Firebase Realtime Database 
  ├── utils/ # (Optional) Helper classes or shared logic
  ├── res/ │
  ├── layout/ # XML files for UI design of each screen 
  ├── drawable/ # App icons, button graphics, background images 
  ├── values/ # colors.xml, strings.xml, themes.xml etc.
  ├── AndroidManifest.xml # App configuration and activity declarations
  ├── build.gradle # Module-level Gradle config 
  └── google-services.json # Firebase configuration file (not committed to GitHub) ``` </pre>
