package vanopt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import vanopt.algorithm.VanLoadingOptimizer;
import vanopt.dto.Shipment;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VanoptApplicationTests {
	@Test
	void contextLoads() {
	}

	@Test
	void shouldChooseOptimalShipments() {

		VanLoadingOptimizer optimizer =
				new VanLoadingOptimizer();

		List<Shipment> shipments =
				new ArrayList<>();

		Shipment a = new Shipment();
		a.setName("A");
		a.setVolume(10);
		a.setRevenue(100);

		Shipment b = new Shipment();
		b.setName("B");
		b.setVolume(5);
		b.setRevenue(60);

		Shipment c = new Shipment();
		c.setName("C");
		c.setVolume(5);
		c.setRevenue(60);

		shipments.add(a);
		shipments.add(b);
		shipments.add(c);

		List<Shipment> result =
				optimizer.optimize(10, shipments);

		int totalRevenue = 0;

		for(Shipment shipment : result) {
			totalRevenue += shipment.getRevenue();
		}

		assertEquals(120, totalRevenue);
	}

	@Test
	void shouldReturnEmptyListWhenNothingFits() {

		VanLoadingOptimizer optimizer =
				new VanLoadingOptimizer();

		List<Shipment> shipments =
				new ArrayList<>();

		Shipment a = new Shipment();
		a.setName("A");
		a.setVolume(100);
		a.setRevenue(500);

		shipments.add(a);

		List<Shipment> result =
				optimizer.optimize(10, shipments);

		assertTrue(result.isEmpty());
	}
	@Test
	void shouldChooseShipmentsThatExactlyFitCapacity() {

		VanLoadingOptimizer optimizer =
				new VanLoadingOptimizer();

		List<Shipment> shipments =
				new ArrayList<>();

		Shipment a = new Shipment();
		a.setName("A");
		a.setVolume(4);
		a.setRevenue(50);

		Shipment b = new Shipment();
		b.setName("B");
		b.setVolume(6);
		b.setRevenue(70);

		Shipment c = new Shipment();
		c.setName("C");
		c.setVolume(5);
		c.setRevenue(40);

		shipments.add(a);
		shipments.add(b);
		shipments.add(c);

		List<Shipment> result =
				optimizer.optimize(10, shipments);

		int totalVolume = 0;
		int totalRevenue = 0;

		for(Shipment shipment : result) {
			totalVolume += shipment.getVolume();
			totalRevenue += shipment.getRevenue();
		}

		assertEquals(10, totalVolume);

		assertEquals(120, totalRevenue);
	}

}
