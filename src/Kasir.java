import java.util.Scanner;

public class Kasir {
    private Meja[] daftarMeja;
    private Menu[] daftarMenu;

    public Kasir() {
        daftarMeja = new Meja[10];
        for (int i = 0; i < 10; i++) {
            daftarMeja[i] = new Meja(i + 1);
        }

        daftarMenu = new Menu[5];
        daftarMenu[0] = new Menu("Nasi Goreng", 15000);
        daftarMenu[1] = new Menu("Mi Goreng", 15000);
        daftarMenu[2] = new Menu("Capcay", 20000);
        daftarMenu[3] = new Menu("Bihun Goreng", 17000);
        daftarMenu[4] = new Menu("Ayam Koloke", 25000);
    }

    // digunakan untuk menampilkan daftar meja beserta keterangan ketersediaannya
    // gunakan method isKosong pada class Kasir agar lebih mudah
    public void tampilkanDaftarMeja() {
        // EDIT DISINI
        System.out.println("Daftar Meja: ");
        for(int i = 0; i < daftarMeja.length; i++){
            if(daftarMeja[i].isKosong() == true){
                System.out.printf("Meja %d (kosong)\n", i+1);
            }
            else System.out.printf("Meja %d (terisi oleh pelanggan %s)\n", i+1, daftarMeja[i].getPelanggan().getNama());
        }
    }

    // untuk menambahkan pelanggan pada meja tertentu
    // jika meja kosong tambahkan pelanggan pada meja tersebut
    // jika tidak buatlah keterangan bahwa meja sudah ada pelanggan
    public void tambahPelanggan(int nomorMeja, Pelanggan pelanggan) {
        // EDIT DISINI
        if(!(daftarMeja[nomorMeja - 1].isKosong())) System.out.println("Meja sudah terisi");
        else{
            daftarMeja[nomorMeja - 1].setPelanggan(pelanggan);
        }
    }

    // menambah pesanan menu pada nomor meja
    // jika menu tidak ada dalam daftar maka tampilkan "Menu is null"
    public void tambahPesanan(int nomorMeja, Menu menu) {
        // EDIT DISINI
        daftarMeja[nomorMeja - 1].setMenu(menu);
    }

    // Menghapus pelanggan
    public void hapusPelanggan(int nomorMeja) {
        // EDIT DISINI
        if(daftarMeja[nomorMeja - 1].isKosong()) System.out.println("Tidak ada pelanggan di meja tersebut!");
        else{
            daftarMeja[nomorMeja - 1].setPelanggan(null);
            System.out.printf("Berhasil menghapus pelanggan untuk meja %d\n", nomorMeja);
        }
    }

    public int hitungHargaPesanan(int nomorMeja) {
        int totalHarga = 0;
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    totalHarga += menu[i].getHarga();
                }
            }
            return totalHarga;
        }
        return totalHarga;
    }

    public void tampilkanPesanan(int nomorMeja) {
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    System.out.println("Meja " + nomorMeja + " - " + pelanggan.getNama() + " memesan "
                            + menu[i].getNama() + " seharga " + menu[i].getHarga());
                }
            }
        } else {
            System.out.println("Meja " + nomorMeja + " tidak memiliki pesanan");
        }
    }

    public void tampilkanDaftarMenu() {
        System.out.println("Daftar Menu:");
        System.out.println("1. Nasi Goreng - Rp15.000");
        System.out.println("2. Mi Goreng - Rp15.000");
        System.out.println("3. Capcay - Rp20.000");
        System.out.println("4. Bihun Goreng - Rp17.000");
        System.out.println("5. Ayam Koloke - Rp25.000");
        System.out.println("6. Simpan");
        System.out.println();
    }

    public void tampilkanDaftarFitur() {
        System.out.println("1. Tampilkan daftar meja");
        System.out.println("2. Tambah pelanggan");
        System.out.println("3. Tambah pesanan");
        System.out.println("4. Hapus pelanggan");
        System.out.println("5. Hitung harga pesanan");
        System.out.println("6. Tampilkan pesanan di meja");
        System.out.println("0. Keluar");
    }

    public void jalankan() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;
        while (pilihan != 0) {
            tampilkanDaftarFitur();
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {
                case 1:
                    // menampilkan daftar meja dengan method yang sudah ada
                    // EDIT DISINI
                    tampilkanDaftarMeja();
                    break;
                case 2:{
                    // tampilkan pesan untuk input nomor meja dan nama pelanggan untuk digunakan
                    // pada method
                    // jangan lupa instansiasi Pelanggan dengan nama pelanggan sesuai input
                    // EDIT DISINI
                    System.out.print("Nomor meja: ");
                    int nomorMeja = scanner.nextInt();
                    System.out.print("Nama pelanggan: ");
                    scanner.nextLine();
                    String nama = scanner.nextLine();
                    if(nomorMeja > 10) System.out.println("Nomor Meja Invalid");
                    else{
                        Pelanggan pelangganN = new Pelanggan(nama);
                        tambahPelanggan(nomorMeja, pelangganN);
                    }
                    break;
                }
                case 3:{
                    boolean stopLoop = false;
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaPesan = scanner.nextInt();
                    if(nomorMejaPesan > 10) System.out.println("ERROR: Nomor meja invalid");
                    else{
                        Boolean meja = daftarMeja[nomorMejaPesan - 1].isKosong();
                        scanner.nextLine();
                        if (!meja) {
                            tampilkanDaftarMenu();
                            while (!stopLoop) {
                                System.out.print("Masukkan nomor menu: ");
                                int nomorMenuPesan = scanner.nextInt();
                                scanner.nextLine();
                                switch (nomorMenuPesan) {
                                    case 1:
                                        tambahPesanan(nomorMejaPesan, daftarMenu[0]);
                                        break;
                                    case 2:
                                        tambahPesanan(nomorMejaPesan, daftarMenu[1]);
                                        break;
                                    case 3:
                                        tambahPesanan(nomorMejaPesan, daftarMenu[2]);
                                        break;
                                    case 4:
                                        tambahPesanan(nomorMejaPesan, daftarMenu[3]);
                                        break;
                                    case 5:
                                        tambahPesanan(nomorMejaPesan, daftarMenu[4]);
                                        break;
                                    case 6:
                                        stopLoop = true;
                                        break;
                                    default:
                                        System.out.println("Nomor menu tidak valid");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Meja belum terisi oleh pelanggan");
                        } 
                    }
                    break;
                }
                case 4:{
                    // untuk menghapus pelanggan pada meja tertentu
                    // tampilkan pesan untuk memasukkan nomor meja yang akan dihapus untuk digunakan
                    // pada method hapusPelanggan()
                    // EDIT DISINI
                    System.out.print("Masukkan nomor meja yang ingin dihapus pelanggannya: ");
                    int nomorMeja = scanner.nextInt();
                    hapusPelanggan(nomorMeja);
                    break;
                }
                case 5:{
                    // Untuk melihat total harga pesanan pada meja tertentu
                    // tampilkan pesan untuk memasukkan nomor meja
                    // jangan lupa membedakan keluaran apabila pelanggan belum memesan apapun /
                    // total harga 0
                    // EDIT DISINI
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMeja = scanner.nextInt();
                    Meja meja = daftarMeja[nomorMeja - 1];
                    Menu[] daftarMenuMeja = meja.getMenu();
                    int harga = 0;
                    for(int i = 0; i < daftarMenuMeja.length; i++){
                        if(daftarMenuMeja[i] != null){
                            harga += daftarMenuMeja[i].getHarga();
                        }
                        else {
                            System.out.printf("Meja %d tidak memiliki pesanan.\n", nomorMeja);
                            break;
                        }
                    }
                    if(harga > 0) System.out.printf("Harga pesanan di meja %d adalah %d\n", nomorMeja, harga);
                    break;
                }
                    
                case 6:{
                    // untuk melihat pesanan pada meja tertentu
                    // tampilkan pesan untuk memasukkan nomor meja
                    // EDIT DISINI
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMeja = scanner.nextInt();
                    tampilkanPesanan(nomorMeja);
                    break;
                }
                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi kasir restoran!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}