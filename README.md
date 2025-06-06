# Bear it 4U - Car Service Scheduling App

**Bear it 4U** is an Android mobile application developed to simplify and streamline the process of scheduling car servicing. The app connects users (vehicle owners) with local service providers, enabling appointment bookings, profile management, service tracking, and communication — all in one place.

---

## ✨ Features

### 👤 For Users

* User registration and login
* View and edit personal profile
* Search service providers by city, name, and service type
* Book car service appointments
* Choose between vehicle drop-off or pick-up
* View appointment details and history
* Receive reminders (pending full implementation)

### 📆 For Service Providers

* Login and manage user accounts
* Add, view, update, and delete service records
* Manage appointments and send reminders
* Generate service reports

---

## 📂 Architecture Overview

### 📊 Project Structure

* **28 Java classes**
* **24 Layout XMLs**
* **20 Activities**
* **RecyclerViews** and **ArrayLists** for dynamic UI rendering

### ⚖️ Database

Implemented using **SQLite** via a custom `DataBaseHelper` class.

#### Tables:

* `SP_table`: Service Provider data
* `User_table`: User profiles
* `Vehicle_table`: Vehicle records
* `Service_table`: Appointments and services
* `Reminder_table`: Reminder tracking

---

## 🚀 Technologies Used

* **Java** – Backend logic
* **XML** – UI layout design
* **Android Studio** – IDE
* **SQLite** – Local database
* **GitHub** – Version control ([Repository Link](https://github.com/ThisisEdgar/ProjectMobileApp.git))

---

## 🔧 Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/ThisisEdgar/ProjectMobileApp.git
```

2. Open the project in Android Studio.

3. Connect an Android device or use an emulator.

4. Build and run the app.

---

## 🪄 Contributors

| Name                  | Responsibilities                                                     |
| --------------------- | -------------------------------------------------------------------- |
| **Sichao Kan**        | Database design, service provider modules, appointment logic         |
| **Edgar Esponda**     | UI navigation, splash and login screens, main activity, booking flow |
| **Fernando Trujillo** | UI layout design, database helper implementation, registration forms |

---

## ⚡ Features Under Development

* Password confirmation input
* Input validation
* Reminder notification system
* Smooth transitions between layouts
* UI design enhancements (colors, imagery)

---

## 🌐 License

This project is developed for academic purposes and does not currently include a commercial license.

---

## 🔍 Screenshots / UI Preview *(Optional)*

*Add screenshots or screen recordings here to showcase key parts of your app.*

---

## ✉ Contact

For questions or collaboration inquiries, feel free to reach out through GitHub issues or contact contributors directly.

---

> "Bear it 4U" - We make car service booking simple.
