package tools;

import java.io.File;
import java.io.IOException;

import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

public class UnzipTarGz {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = "/Users/zachyang/Documents/Ensighten/new";
		File file = new File(path+"/rentrak_viacom_20150420_20150712.tar.gz");
		Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
		archiver.extract(file, new File(path));
	}

}
