package com.irza.brawithu.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.irza.brawithu.model.berita.BeritaModel
import com.irza.brawithu.model.konseling.KonselingModel
import com.irza.brawithu.model.lapor.LaporModel
import com.irza.brawithu.model.sos.SOSModel
import com.irza.brawithu.model.user.UserModel
import kotlinx.coroutines.tasks.await
import java.util.UUID

class Repository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(
        email : String,
        password : String,
        nama: String,
        onSuccess: () -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                firestore
                    .collection("user")
                    .document(it.user?.uid ?: "")
                    .set(
                        UserModel(
                            uid = it.user?.uid ?: "",
                            email = it.user?.email ?: "",
                            nama = nama,
                            progstud = "",
                            nim = "",
                            angkatan = "",
                            fakultas = "",
                            jurusan = "",
                            jenjang = "",
                            status = "",
                            role = ""
                        )
                    )
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFailed(it)
                    }
            }
            .addOnFailureListener{
                onFailed(it)
            }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val uid = auth.currentUser?.uid
                if (uid != null) {
                    val db = FirebaseFirestore.getInstance()
                    val userRef = db.collection("user").document(uid)

                    userRef.get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                val role = document.getString("role") ?: "user"
                                onSuccess(role)
                            } else {
                                onFailed(Exception("User data not found"))
                            }
                        }
                        .addOnFailureListener { exception ->
                            onFailed(exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                onFailed(exception)
            }
    }

    fun logout(onSuccess: () -> Unit, onFailed: (Exception) -> Unit) {
        try {
            auth.signOut()
            onSuccess()
        } catch (exception: Exception) {
            onFailed(exception)
        }
    }


    fun getUser(
        uid: String,
        onSuccess: (UserModel) -> Unit,
        onFailed: (Exception) -> Unit,
    ){
        firestore
            .collection("user")
            .document(uid)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        UserModel(
                            uid = auth.uid ?: "",
                            email = auth.currentUser?.email ?: "",
                            nama = doc["nama"] as String,
                            status = doc["status"] as String,
                            role = doc["role"] as String,
                            progstud = doc["progstud"] as String,
                            nim = doc["nim"] as String,
                            angkatan = doc["angkatan"] as String,
                            fakultas = doc["fakultas"] as String,
                            jurusan = doc["jurusan"] as String,
                            jenjang = doc["jenjang"] as String
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }

    suspend fun generateLaporUniqueRandomId(): String {
        val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        while (true) {
            val part1 = (1..4).map { allowedChars.random() }.joinToString("")
            val part2 = (1..4).map { allowedChars.random() }.joinToString("")
            val id = "$part1-$part2"

            val snapshot = firestore.collection("lapor").document(id).get().await()
            if (!snapshot.exists()) {
                return id
            }
        }
    }

    suspend fun generateKonselingUniqueRandomId(): String {
        val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        while (true) {
            val part1 = (1..4).map { allowedChars.random() }.joinToString("")
            val part2 = (1..4).map { allowedChars.random() }.joinToString("")
            val id = "$part1-$part2"

            val snapshot = firestore.collection("konseling").document(id).get().await()
            if (!snapshot.exists()) {
                return id
            }
        }
    }

    suspend fun generateSOSUniqueRandomId(): String {
        val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        while (true) {
            val part1 = (1..4).map { allowedChars.random() }.joinToString("")
            val part2 = (1..4).map { allowedChars.random() }.joinToString("")
            val id = "$part1-$part2"

            val snapshot = firestore.collection("sos").document(id).get().await()
            if (!snapshot.exists()) {
                return id
            }
        }
    }

    suspend fun postKonseling(
        nama: String,
        nim: String,
        jenis_kelamin: String,
        jam: String,
        fakultas: String,
        onSuccess: (String) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        val id = generateKonselingUniqueRandomId()
        firestore
            .collection("konseling")
            .document(id)
            .set(
                KonselingModel(
                    id = id,
                    user_id = auth.currentUser?.uid ?: "",
                    nama = nama,
                    nim = nim,
                    jenis_kelamin = jenis_kelamin,
                    jam = jam,
                    fakultas = fakultas,
                    status = "Selesai Konseling"
                )
            )
            .addOnSuccessListener {
                onSuccess(id)
            }
            .addOnFailureListener {
                onFailed(it)
            }
    }

    fun getAllKonseling(
        onSuccess: (List<KonselingModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("konseling")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            KonselingModel(
                                id = doc?.getString("id") ?: "",
                                user_id = doc?.getString("user_id") ?: "",
                                jenis_kelamin = doc?.getString("jenis_kelamin") ?: "",
                                nama = doc?.getString("nama") ?: "",
                                nim = doc?.getString("nim") ?: "",
                                jam = doc?.getString("jam") ?: "",
                                fakultas = doc?.getString("fakultas") ?: "",
                                status = doc?.getString("status") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    suspend fun postSOS(
        nama: String,
        fakultas: String,
        onSuccess: (String) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        val id = generateSOSUniqueRandomId()
        firestore
            .collection("sos")
            .document(id)
            .set(
                SOSModel(
                    id = id,
                    user_id = auth.currentUser?.uid ?: "",
                    nama = nama,
                    fakultas = fakultas,
                    status = "Diterima 04:40:37",
                    lokasi = "Klojen, Malang"
                )
            )
            .addOnSuccessListener {
                onSuccess(id)
            }
            .addOnFailureListener {
                onFailed(it)
            }
    }

    fun getAllSOS(
        onSuccess: (List<SOSModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("sos")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            SOSModel(
                                id = doc?.getString("id") ?: "",
                                user_id = doc?.getString("user_id") ?: "",
                                nama = doc?.getString("nama") ?: "",
                                fakultas = doc?.getString("fakultas") ?: "",
                                status = doc?.getString("status") ?: "",
                                lokasi = doc?.getString("lokasi") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    suspend fun postLapor(
        nama: String,
        nim: String,
        jenis_kelamin: String,
        lokasi: String,
        detail: String,
        onSuccess: (String) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        val id = generateLaporUniqueRandomId()
        firestore
            .collection("lapor")
            .document(id)
            .set(
                LaporModel(
                    id = id,
                    user_id = auth.currentUser?.uid ?: "",
                    nama = nama,
                    nim = nim,
                    jenis_kelamin = jenis_kelamin,
                    lokasi = lokasi,
                    detail = detail,
                    status = "Sedang Ditangani"
                )
            )
            .addOnSuccessListener {
                onSuccess(id)
            }
            .addOnFailureListener {
                onFailed(it)
            }
    }

    fun getAllLapor(
        onSuccess: (List<LaporModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("lapor")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            LaporModel(
                                id = doc?.getString("id") ?: "",
                                user_id = doc?.getString("user_id") ?: "",
                                detail = doc?.getString("detail") ?: "",
                                jenis_kelamin = doc?.getString("jenis_kelamin") ?: "",
                                lokasi = doc?.getString("lokasi") ?: "",
                                nama = doc?.getString("nama") ?: "",
                                nim = doc?.getString("nim") ?: "",
                                status = doc?.getString("status") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getAllLaporByUserId(
        onSuccess: (List<LaporModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("lapor")
            .whereEqualTo("user_id", auth.currentUser?.uid ?: "")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            LaporModel(
                                id = doc?.getString("id") ?: "",
                                user_id = doc?.getString("user_id") ?: "",
                                detail = doc?.getString("detail") ?: "",
                                nama = doc?.getString("nama") ?: "",
                                nim = doc?.getString("nim") ?: "",
                                jenis_kelamin = doc?.getString("jenis_kelamin") ?: "",
                                lokasi = doc?.getString("lokasi") ?: "",
                                status = doc?.getString("status") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getAllBerita(
        onSuccess: (List<BeritaModel>) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        firestore
            .collection("berita")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                    return@addSnapshotListener
                }
                value?.let {
                    onSuccess(
                        it.documents.map { doc ->
                            BeritaModel(
                                id = doc?.getString("id") ?: "",
                                judul = doc?.getString("judul") ?: "",
                                isi = doc?.getString("isi") ?: "",
                                tanggal = doc?.getString("tanggal") ?: ""
                            )
                        }
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun getBeritaById(
        id: String,
        onSuccess: (BeritaModel) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("berita")
            .document(id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        BeritaModel(
                            id = doc["id"] as String,
                            judul = doc["judul"] as String,
                            isi = doc["isi"] as String,
                            tanggal = doc["tanggal"] as String
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }
}