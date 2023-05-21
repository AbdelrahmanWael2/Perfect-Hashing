import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ON2_DictionaryTest {
    @org.junit.jupiter.api.Test
    void insert() {
        ON2_Dictionary d2 = new ON2_Dictionary(5);
        assertEquals("Ahmed inserted successfully",d2.insert("Ahmed"));
        assertEquals("Abdo inserted successfully",d2.insert("Abdo"));
        assertEquals("belal inserted successfully",d2.insert("belal"));
        assertEquals("Ahmed already exists",d2.insert("Ahmed"));
    }
    //    the tests affect each other
    @org.junit.jupiter.api.Test
    void search() {
        ON2_Dictionary d2 = new ON2_Dictionary(5);

        assertEquals("Mohamed inserted successfully",d2.insert("Mohamed"));
        assertEquals("Mohamed found successfully",d2.search("Mohamed"));
        assertEquals("Ali not found",d2.search("Ali"));

    }

    @org.junit.jupiter.api.Test
    void delete() {
        ON2_Dictionary d2 = new ON2_Dictionary(4);
        assertEquals("Mohamed inserted successfully",d2.insert("Mohamed"));
        assertEquals(true,d2.delete("Mohamed"));
        assertEquals(false,d2.delete("Ali"));
        assertEquals(false,d2.delete("Ali"));

    }

    @org.junit.jupiter.api.Test
    void batchInsert() throws IOException {
        ON2_Dictionary d2 = new ON2_Dictionary(1000000);

        assertEquals("8 items added and 999992 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt"));
        assertEquals("0 items added and 1000000 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt"));
        assertEquals("1000000 items added and 0 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test2.txt"));

    }

    @org.junit.jupiter.api.Test
    void batchDelete() throws IOException {
        ON2_Dictionary d2 = new ON2_Dictionary(1000000);
        assertEquals("1000000 items added and 0 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test2.txt"));
        assertEquals("1000000 items deleted 0 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test2.txt"));
        assertEquals("0 items deleted 1000000 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt"));

    }
    @org.junit.jupiter.api.Test
    void test1(){
        ON2_Dictionary d2 = new ON2_Dictionary(6);
        assertEquals("computer inserted successfully",d2.insert("computer"));
        assertEquals("Ali not found",d2.search("Ali"));
        assertEquals("belal inserted successfully",d2.insert("belal"));
        assertEquals(false,d2.delete("Ali"));
    }
    @org.junit.jupiter.api.Test
    void test2() throws IOException {
        ON2_Dictionary d2 = new ON2_Dictionary(1000000);
        assertEquals("raffek inserted successfully",d2.insert("raffek"));
        assertEquals(false,d2.delete("Ali"));
        assertEquals("Ali not found",d2.search("Ali"));
        assertEquals("0 items deleted 1000000 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt"));
        assertEquals("8 items added and 999992 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt"));
        assertEquals("1000000 items added and 0 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test2.txt"));
        assertEquals(true, d2.delete("mango"));
        assertEquals("7 items deleted 999993 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test.txt"));
    }
    //    small files to batch insert and delete from it then batch delete it
    @org.junit.jupiter.api.Test
    void test3() throws IOException {
        ON2_Dictionary d2 = new ON2_Dictionary(6);
        assertEquals("5 items added and 3 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test3.txt"));
//        deleted element from the 5 then we batch delete the file we will see that 4 items is deleted only not file
        assertEquals(true , d2.delete("magdy"));
        assertEquals(false , d2.delete("magdy"));
        assertEquals("magdy not found" , d2.search("magdy"));
        assertEquals("raffek found successfully", d2.search("raffek"));
        assertEquals("4 items deleted 4 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test3.txt"));
    }
    // small batch insert then insert on the current table then batch delete
    @org.junit.jupiter.api.Test
    void test4() throws IOException {
        ON2_Dictionary d2 = new ON2_Dictionary(6);
        assertEquals("5 items added and 3 not exist",d2.batchInsert("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test3.txt"));
        assertEquals("belal inserted successfully",d2.insert("belal"));
        assertEquals("belal found successfully",d2.search("belal"));
        assertEquals("magdy already exists",d2.insert("magdy"));
    }
    // insert word in the table that is in the small file then delete the small file
//    this test case fails because when we insert Ali which is not item in file Test3.txt
// then try to batchDelete it shouldn't tell me that he deleted any element but it gives me that 1 items deleted 7 items not exist
//    that is not true because there is no item in the file is deleted
    @org.junit.jupiter.api.Test
    void test5() throws IOException {
        ON2_Dictionary d2 = new ON2_Dictionary(6);
        assertEquals("magdy inserted successfully", d2.insert("magdy"));
        assertEquals("1 items deleted 7 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test3.txt"));
        assertEquals("magdy not found", d2.search("magdy"));
        assertEquals("Ali inserted successfully",d2.insert("Ali"));
        assertEquals("0 items deleted 8 items not exist",d2.batchDelete("D:\\CSED2_2\\DS\\labs\\hashing\\Perfect-Hashing\\src\\Test3.txt"));
        assertEquals("Ali found successfully", d2.search("Ali"));

    }

}