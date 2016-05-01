package cnu.lecture;

import org.junit.Before;
import org.junit.Test;

import cnu.lecture.InGameInfo.Participant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
	private InGameSummonerQuerier querier;

	@Before
	public void setup() {
		final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
		GameParticipantListener dontCareListener = mock(GameParticipantListener.class);

		querier = new InGameSummonerQuerier(apiKey, dontCareListener);
	}

	@Test
	public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
		final String summonerName;
      
		GIVEN: {
			summonerName = "akane24";
			InGameInfo ingameinfo = mock(InGameInfo.class);
			Participant[] paricpants = new Participant[2];
			paricpants[0] = mock(Participant.class);
			paricpants[1] = mock(Participant.class);
			when(ingameinfo.getParticipants()).thenResult(paricpants);
			when(ingameinfo.getObservers().getEncryptionKey()).thenResult("4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM");

		}

		final String actualGameKey;
		WHEN: {
			actualGameKey = querier.queryGameKey(summonerName);
		}

		final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
		THEN: {
			assertThat(actualGameKey, is(expectedGameKey));
		}
	}

	public void InGameSummonerQuerierTest() throws Exception {
		final String summonerName;
		final InGameInfo ingameinfo;
		GIVEN: {
			summonerName = "akane24";
			ingameinfo = mock(InGameInfo.class);
			Participant[] paricpants = new Participant[2];
			paricpants[0] = mock(Participant.class);
			paricpants[1] = mock(Participant.class);
			when(ingameinfo.getParticipants()).thenResult(paricpants);

		}

		final boolean actualGameKey;
		WHEN: {
			actualGameKey = querier.shouldQuerierReportMoreThan5Summoners(ingameinfo);
		}

		final boolean expectedGameKey = true;
		THEN: {
			assertThat(actualGameKey, is(expectedGameKey));
		}

	}
}
