package com.example.overcast;

import static org.awaitility.Awaitility.await;
import static org.assertj.core.api.Assertions.assertThat;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = { OvercastConfiguration.class, AppConfiguration.class }, 
	webEnvironment = WebEnvironment.NONE
)
public class AppComponentTest {
	@Autowired private AppQueueMessageSender sender;
	@Autowired private AppMessageRepository repository;
	
	@Test
	public void testThatMessageHasBeenPersisted() {
		sender.send("Test Message!");
		await().atMost(1, SECONDS).until(() -> repository.size() > 0);
		assertThat(repository.readAll()).containsExactly("Test Message!");
	}
}
