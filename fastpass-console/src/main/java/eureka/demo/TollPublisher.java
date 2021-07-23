package eureka.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.annotation.Poller;

//@EnableBinding(Source.class)
@EnableBinding(TollSource.class)
public class TollPublisher {
	static int countId = 0;

//	@InboundChannelAdapter(channel = Source.OUTPUT)
//	public String sendTollCharge() {
//		countId++;
//		System.out.println("{station:\"20\", customerid:\"" + countId + "100\", timestamp:\"2019-11-09-12T03:15:00\"}");
//		return "{station:\"20\", customerid:\"" + countId + "100\", timestamp:\"2019-11-09-12T03:15:00\"}";
//	}

	@Bean
	@InboundChannelAdapter(channel = "fastpassTollChannel", poller = @Poller(fixedDelay = "2000"))
	public MessageSource<Toll> sendTollCharge() {
		return () -> MessageBuilder.withPayload(new Toll("20", "100", "2019-11-09T12:04:00")).build();
	}
	
	class Toll
	{
		public String stationId; 
		public String customerId;
		public String timestamp;
		
		public Toll(String stationId, String customerId, String timestamp)
		{
			this.stationId= stationId;
			this.customerId=customerId;
			this.timestamp=timestamp;
		}
	}

}