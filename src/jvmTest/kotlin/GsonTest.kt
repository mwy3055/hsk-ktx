import com.google.gson.Gson
import com.hsk.ktx.fromJson
import com.hsk.ktx.toJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GsonTest {

    data class User(
        val name: String,
        val value: Int,
    )

    @Test
    fun fromJson_JsonToSomeClass() {
        val json = """
            {"name":"hsk","value":10}
        """.trimIndent()

        val data = User(
            name = "hsk",
            value = 10
        )
        assertThat(Gson().fromJson<User>(json)).isEqualTo(data)
    }

    @Test
    fun toJson_ListToJsonString() {
        val users = (1..10).map { User(name = "hsk", value = it) }
        val json =
            """[{"name":"hsk","value":1},{"name":"hsk","value":2},{"name":"hsk","value":3},{"name":"hsk","value":4},{"name":"hsk","value":5},{"name":"hsk","value":6},{"name":"hsk","value":7},{"name":"hsk","value":8},{"name":"hsk","value":9},{"name":"hsk","value":10}]"""
        assertThat(users.toJson()).isEqualTo(json)
    }
}