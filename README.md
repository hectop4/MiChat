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

## 🛠️ Dependencias Principales

```kotlin
// UI y Compose
implementation("androidx.activity:activity-compose:1.8.2")
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

// Navegación
implementation("androidx.navigation:navigation-compose:2.7.6")

// Inyección de dependencias
implementation("com.google.dagger:hilt-android:2.48")
implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

// Base de datos local
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")

// Firebase
implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
implementation("com.google.firebase:firebase-firestore-ktx")

// Corrutinas
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

## 🏃‍♂️ Ejecutar la Aplicación

### En Emulador
1. Abre Android Studio
2. Crea un AVD (Android Virtual Device) con API 24+
3. Ejecuta: **Run → Run 'app'**

### En Dispositivo Físico
1. Habilita **"Opciones de desarrollador"** en tu dispositivo
2. Activa **"Depuración USB"**
3. Conecta el dispositivo via USB
4. Ejecuta: **Run → Run 'app'**

### Probar Chat Multidispositivo
1. Ejecuta la app en 2+ dispositivos/emuladores
2. Registra usuarios con nombres diferentes
3. Envía mensajes desde cualquier dispositivo
4. Observa la sincronización en tiempo real

## 🔧 Configuración Avanzada

### Reglas de Firestore (Producción)
Para producción, actualiza las reglas de Firestore:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /messages/{document} {
      allow read, write: if true; // Cambiar por autenticación real
    }
  }
}
```

### Variables de Entorno
Puedes configurar diferentes entornos editando:
- `app/build.gradle.kts` → `buildTypes`
- Crear múltiples archivos `google-services.json` para dev/prod

## 🐛 Troubleshooting

### Error: "Could not find google-services.json"
- Verifica que el archivo esté en `app/google-services.json`
- Sincroniza el proyecto nuevamente

### Error: "FirebaseApp is not initialized"
- Asegúrate de que Firebase esté configurado correctamente
- Verifica que el archivo `google-services.json` sea válido

### Mensajes no aparecen en tiempo real
- Verifica la conexión a Internet
- Comprueba las reglas de Firestore
- Revisa los logs de Android Studio

### Error de compilación con Hilt
- Limpia el proyecto: **Build → Clean Project**
- Reconstruye: **Build → Rebuild Project**

## 📝 Notas de Desarrollo

### Patrones Implementados
- **Repository Pattern**: Abstrae el acceso a datos
- **Use Case Pattern**: Encapsula lógica de negocio
- **Observer Pattern**: UI reactiva con Flow
- **Dependency Injection**: Gestión automática de dependencias

### Principios SOLID
- **Single Responsibility**: Cada clase tiene una responsabilidad única
- **Open/Closed**: Extensible sin modificar código existente
- **Dependency Inversion**: Dependencias abstraídas por interfaces

### Clean Architecture
- **Domain**: Lógica de negocio independiente
- **Data**: Acceso a datos (local y remoto)
- **Presentation**: UI y estados de la aplicación

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo `LICENSE` para detalles.

## 👥 Autor

Desarrollado como ejemplo de aplicación de chat con arquitectura moderna de Android.

---

## 🚀 Próximas Mejoras

- [ ] Autenticación de usuarios
- [ ] Salas de chat privadas
- [ ] Envío de imágenes
- [ ] Notificaciones push
- [ ] Estados de mensaje (enviado/leído)
- [ ] Modo oscuro
- [ ] Encriptación end-to-end

