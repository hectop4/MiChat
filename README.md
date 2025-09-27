# MiChat - Aplicación de Chat en Tiempo Real

Una aplicación de mensajería simple desarrollada con **Kotlin**, **Jetpack Compose**, **MVVM**, **Clean Architecture** y **Firebase** como backend.

## 📱 Características

- ✅ **Chat en tiempo real** entre múltiples dispositivos
- ✅ **Registro simple** con solo nombre de usuario
- ✅ **Interfaz moderna** con Jetpack Compose
- ✅ **Arquitectura limpia** siguiendo Clean Architecture y MVVM
- ✅ **Persistencia local** con Room Database
- ✅ **Backend en la nube** con Firebase Firestore
- ✅ **Sincronización automática** entre dispositivos
- ✅ **Modo offline** con cache local

## 🏗️ Arquitectura

### Stack Tecnológico
- **Lenguaje**: Kotlin
- **UI**: Jetpack Compose + Material 3
- **Arquitectura**: Clean Architecture + MVVM
- **Inyección de Dependencias**: Hilt
- **Base de datos local**: Room
- **Backend**: Firebase Firestore
- **Navegación**: Navigation Compose
- **Programación reactiva**: Coroutines + Flow

### Estructura del Proyecto
```
📦 com.example.michat
├── 📂 domain/                 # Capa de Dominio (Lógica de negocio)
│   ├── 📂 model/             # Modelos de dominio
│   │   ├── Message.kt
│   │   └── User.kt
│   ├── 📂 repository/        # Interfaces de repositorios
│   │   ├── MessageRepository.kt
│   │   └── UserRepository.kt
│   └── 📂 usecase/          # Casos de uso
│       ├── GetCurrentUserUseCase.kt
│       ├── GetMessagesUseCase.kt
│       ├── SaveUserUseCase.kt
│       └── SendMessageUseCase.kt
├── 📂 data/                  # Capa de Datos
│   ├── 📂 local/            # Base de datos local (Room)
│   │   ├── 📂 dao/
│   │   │   └── MessageDao.kt
│   │   ├── 📂 database/
│   │   │   └── AppDatabase.kt
│   │   └── 📂 entity/
│   │       └── MessageEntity.kt
│   ├── 📂 remote/           # Backend remoto (Firebase)
│   │   ├── 📂 mapper/
│   │   │   └── FirebaseMapper.kt
│   │   └── FirebaseMessageService.kt
│   ├── 📂 mapper/           # Mappers locales
│   │   └── MessageMapper.kt
│   └── 📂 repository/       # Implementaciones de repositorios
│       ├── MessageRepositoryImpl.kt
│       └── UserRepositoryImpl.kt
├── 📂 presentation/          # Capa de Presentación (UI)
│   ├── 📂 login/            # Pantalla de registro
│   │   ├── LoginScreen.kt
│   │   └── LoginViewModel.kt
│   ├── 📂 chat/             # Pantalla de chat
│   │   ├── ChatScreen.kt
│   │   └── ChatViewModel.kt
│   └── 📂 navigation/       # Navegación
│       └── MiChatNavigation.kt
├── 📂 di/                   # Inyección de dependencias
│   └── AppModule.kt
├── 📂 ui/theme/             # Tema de la aplicación
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
├── MainActivity.kt
└── MiChatApplication.kt
```

## 🚀 Instalación y Configuración

### Prerrequisitos
- Android Studio Hedgehog | 2023.1.1 o superior
- JDK 8 o superior
- Android SDK API 24 (Android 7.0) o superior
- Cuenta de Google para Firebase

### 1. Clonar el Proyecto
```bash
git clone <tu-repositorio>
cd MiChat
```

### 2. Configurar Firebase

#### 2.1 Crear Proyecto Firebase
1. Ve a [Firebase Console](https://console.firebase.google.com/)
2. Haz clic en **"Crear un proyecto"**
3. Nombre del proyecto: `MiChat`
4. **Deshabilita** Google Analytics (opcional)
5. Crea el proyecto

#### 2.2 Agregar App Android
1. En el proyecto Firebase, haz clic en **"Agregar app"** → **Android**
2. **Nombre del paquete**: `com.example.michat`
3. **Nombre de la app**: `MiChat`
4. **SHA-1**: Deja vacío (no necesario para Firestore)
5. Descarga el archivo `google-services.json`

#### 2.3 Colocar google-services.json
Coloca el archivo descargado en:
```
MiChat/app/google-services.json
```

#### 2.4 Habilitar Firestore
1. En Firebase Console, ve a **"Firestore Database"**
2. Haz clic en **"Crear base de datos"**
3. Selecciona **"Comenzar en modo de prueba"**
4. Elige una ubicación cercana

### 3. Sincronizar y Compilar
1. Abre el proyecto en Android Studio
2. Sincroniza el proyecto: **File → Sync Project with Gradle Files**
3. Compila: **Build → Make Project**

## 📖 Uso de la Aplicación

### Pantalla de Registro
1. **Ingresa tu nombre** en el campo de texto
2. Presiona **"Entrar al Chat"**
3. El nombre se guarda automáticamente

### Pantalla de Chat
1. **Ver mensajes**: Los mensajes aparecen en tiempo real
   - Mensajes propios: **Lado derecho** (azul)
   - Mensajes de otros: **Lado izquierdo** (gris)
2. **Enviar mensajes**: Escribe en el campo inferior y presiona el botón de envío
3. **Scroll automático**: La lista se desplaza automáticamente al mensaje más reciente

### Múltiples Dispositivos
1. Instala la app en varios dispositivos/emuladores
2. Registra usuarios con **nombres diferentes**
3. Los mensajes enviados desde cualquier dispositivo aparecerán en **tiempo real** en todos los demás

