package edu.ucsb.cs156.spring.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

public class DeveloperTest {

    @Test
    public void testPrivateConstructor() throws Exception {
        // this hack is from https://www.timomeinen.de/2013/10/test-for-private-constructor-to-get-full-code-coverage/
        Constructor<Developer> constructor = Developer.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()),"Constructor is not private");

        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void getName_returns_correct_name() {
        assertEquals("Alec S.", Developer.getName());
    }

    // TODO: Add additional tests as needed to get to 100% jacoco line coverage, and
    // 100% mutation coverage (all mutants timed out or killed)
    @Test
    public void getGithubId_returns_correct_githubId() {
        assertEquals("alecsong222", Developer.getGithubId());
    }

    @Test
    public void getTeam_returns_team_with_correct_name() {
        Team  t = Developer.getTeam();
        assertEquals("f24-09", t.getName());
    }

    @Test
    public void getTeam_returns_team_with_correct_members() {
        Team  t = Developer.getTeam();
        assertTrue(t.getMembers().contains("Hongtao"),"Team should contain Hongtao");
        assertTrue(t.getMembers().contains("Neil"),"Team should contain Neil");
        assertTrue(t.getMembers().contains("Alec"),"Team should contain Alec");
        assertTrue(t.getMembers().contains("Elijah"),"Team should contain Elijah");
        assertTrue(t.getMembers().contains("Nathaniel"),"Team should contain Nathaniel");
        assertTrue(t.getMembers().contains("Tom"),"Team should contain Tom");
    }

    @Test
    public void test_equals_same_obj() {
        //same obj
        Team team1 = new Team("team1");
        team1.addMember("alec");
        team1.addMember("alec2");
        assertTrue(team1.equals(team1));

        //different class
        String notTeam = "not a team";
        assertFalse(team1.equals(notTeam));

        //name and members equal
        Team team2 = new Team("team1");
        team2.addMember("alec");
        team2.addMember("alec2");
        assertTrue(team1.equals(team2));

        //name equal, members not equal
        Team team3 = new Team("team1");
        team3.addMember("alec3");
        team3.addMember("alec4");
        assertFalse(team1.equals(team3));

        //name not equal but members are equal
        Team team4 = new Team("team4");
        team4.addMember("alec");
        team4.addMember("alec2");
        assertFalse(team1.equals(team4));

        //name and members not equal
        Team team5 = new Team("team5");
        team5.addMember("alex");
        team5.addMember("alex2");
        assertFalse(team1.equals(team5));
    }

    @Test
    public void test_hashCode() {
        Team t1 = new Team();
        t1.setName("foo");
        t1.addMember("bar");
        Team t2 = new Team();
        t2.setName("foo");
        t2.addMember("bar");
        assertEquals(t1.hashCode(), t2.hashCode());
        Team t = new Team();
        int result = t.hashCode();
        int expectedResult = 1;
        assertEquals(expectedResult, result);
    }
}
