package hameed.irrigationsystem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class IrrigationSystemApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Adding new Plot Test")
	public void testAddNewPlotOfLand() throws Exception {
		String requestBody = "{ \"name\": \"Plot A\", \"area\": 100 }";

		mockMvc.perform(MockMvcRequestBuilders.post("/plots")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Configuring the Plot of land Test")
	public void testConfigurePlotOfLand() throws Exception {
		String requestBody = "{ \"waterRequired\": 1000.0,\n" +
				"    \"timeSlot\": \n" +
				"    {\n" +
				"        \"startTime\": \"14:00:00\",\n" +
				"        \"endTime\": \"15:30:00\",\n" +
				"        \"status\": \"Reserved\"\n" +
				"    } }";

		mockMvc.perform(MockMvcRequestBuilders.post("/plots/{plotId}/configurations", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Editing the Plot of Land Test")
	public void testEditPlotOfLand() throws Exception {
		String requestBody = "{ \"name\": \"Updated Plot A\", \"area\": 120 }";

		mockMvc.perform(MockMvcRequestBuilders.put("/plots/{plotId}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@DisplayName("Listing all Plots Test")
	public void testListAllPlots() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/plots"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	// I used the plot with id=1 because it is preconfigured to be associated with a sensor that is active
	@Test
	@DisplayName("Irrigating Plot with sensor Active Test")
	public void testIrrigatePlotWithSensorActive() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/plots/1/irrigate"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/plots/1/irrigate/update"));
	}

	// I used the plot with id=2 because it is preconfigured to be associated with a sensor that is inactive
	@Test
	@DisplayName("Irrigating Plot with sensor Inactive Test")
	public void testIrrigatePlotWithSensorInactive() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/plots/2/irrigate"))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Inactive"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Sensor dedicated for the plot with id: 2 is not active at the moment"));
	}
}
