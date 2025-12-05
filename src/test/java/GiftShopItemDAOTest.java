import com.jkn.model.dao.GiftShopItemDAO;
import com.jkn.model.entity.GiftShopItem;
import org.junit.jupiter.api.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GiftShopItemDAOTest {

    @Test
    public void testCreateItem() throws SQLException {
        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.setDatabase("test");

        GiftShopItem item = new GiftShopItem(
                "DK",
                9999,
                "DK won",
                new SerialBlob(new byte[0])
        );

        dao.create(item);

        dao.delete( item.getID() );

        assertAll(
                () -> assertNotNull( item.getID() )
        );
    }

    @Test
    public void testReadItem() throws Exception{
        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.setDatabase("test");

        byte[] imageData = new byte[] {2, 2, 2, 2, 2};
        GiftShopItem item = new GiftShopItem(
                "DK",
                9999,
                "DK won",
                new SerialBlob(imageData)
        );

        dao.create(item);

        GiftShopItem found = dao.read(item.getID());

        dao.delete(item.getID());

        assertAll(
                () -> assertNotNull(item.getID()),
                () -> assertEquals("DK", found.getName()),
                () -> assertEquals(9999, found.getPrice()),
                () -> assertEquals("DK won", found.getDescription()),
                () -> assertArrayEquals(imageData,
                        found.getPicture().getBytes(1, (int) found.getPicture().length()))
        );

    }

    @Test
    public void testReadItemDNE() throws Exception{
        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.setDatabase("test");

        GiftShopItem found = dao.read(999);
        assertNull(found);
    }

    @Test
    public void testListItem() throws Exception{
        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.setDatabase("test");

        List<GiftShopItem> lst = dao.list();
        assertAll(
                () -> assertEquals(0, lst.size())
        );

    }
}
