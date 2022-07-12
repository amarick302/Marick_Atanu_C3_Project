import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    LocalTime openingTime=LocalTime.parse("09:30:00");
    LocalTime closingTime= LocalTime.parse("22:00:00");
    Restaurant restaurant=new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    Restaurant mockRestaurant;
    @BeforeEach
    public void create_simple_Restaurant_Object(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        mockRestaurant=Mockito.mock(Restaurant.class);
    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00"));
        boolean statement=mockRestaurant.getCurrentTime().compareTo(openingTime)>0
                && mockRestaurant.getCurrentTime().compareTo(closingTime)<0;

        assertEquals(true,statement);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Mockito.when(mockRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("09:29:00"));
        boolean statement=mockRestaurant.getCurrentTime().compareTo(openingTime)>0
                && mockRestaurant.getCurrentTime().compareTo(closingTime)<0;

        assertFalse(statement);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //<<<<<<<<<<<<<<<<<<<<<Check Order>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void check_whether_selected_items_successfully_selected(){
        restaurant.addToMenu("Sweet corn soup",119);
        String []arr={"Sweet corn soup"};
        restaurant.orderItems(arr);
        assertEquals("Sweet corn soup",restaurant.orderedList.get(0).getName());
    }
    @Test
    public void check_order_value(){
        restaurant.addToMenu("Sweet corn soup",119);
        String []arr={"Sweet corn soup"};
        restaurant.orderItems(arr);
        assertEquals(119,restaurant.totalOrderValue());
    }
    //<<<<<<<<<<<<<<<<<<<<<Check Order>>>>>>>>>>>>>>>>>>>>>>>>
}