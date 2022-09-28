package pe.isil.ep11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class EvPe1Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EvPe1Application.class, args);

		//Cargar driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		//crear conexion
		Connection conexion = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/ep1", "root", "root");

		//testStatement(conexion);

		//testPreparedStatement(conexion);


		testCallablesStatement(conexion);

		//cerrar conexion
		conexion.close();
	}

	private static void testCallablesStatement(Connection conexion) throws Exception {
		CallableStatement cs = conexion.prepareCall("{call getAllequipo()}");
		ResultSet rs = cs.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString("name") + " " +
					rs.getString("phone") + " " +
					rs.getString("city") + " " +
					rs.getString("correo")
			);
		}
	}


	private static void testPreparedStatementResult(Connection conexion) throws SQLException {
		PreparedStatement pt = conexion.prepareStatement("SELECT * FROM equipo WHERE phone=?");
		pt.setString(1, "984784772");

		ResultSet rs = pt.executeQuery();

		while (rs.next()) {
			System.out.println(rs.getString("name") + "" + rs.getString("city"));

		}
	}

	public static void testStatement(Connection connection) throws Exception {

		//Crear statement
		Statement statement = connection.createStatement();

		//Ejecutar sentencia
		ResultSet resultSet = statement.executeQuery("SELECT * FROM equipo");
		while (resultSet.next()) {
			System.out.println(resultSet.getString("phone"));
		}
	}
}


