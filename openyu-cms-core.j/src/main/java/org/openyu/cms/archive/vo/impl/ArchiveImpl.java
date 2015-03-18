package org.openyu.cms.archive.vo.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.openyu.cms.archive.vo.Archive;
import org.openyu.commons.bean.supporter.BaseBeanSupporter;
import org.openyu.commons.enumz.EnumHelper;
//--------------------------------------------------
//jaxb
//--------------------------------------------------
import org.openyu.commons.io.FileHelper;

@XmlRootElement(name = "archive")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArchiveImpl extends BaseBeanSupporter implements Archive {

	private static final long serialVersionUID = -7786746675149537670L;

	/**
	 * 檔案/目錄
	 */
	@XmlTransient
	private File file;

	/**
	 * 是否有效
	 */
	private boolean valid;

	/**
	 * 父層路徑
	 */
	private String parentAbsolutePath;

	@XmlTransient
	private FileFilter filter;

	@XmlTransient
	private List<Archive> child;

	/**
	 * @param file
	 *            待包裝的文件
	 * @param parentAbsolutePath
	 *            根路徑，用於計算相對路徑
	 * @param filter
	 *            檔篩檢程式，應用於所有子檔
	 * @param valid
	 *            是否有效
	 */
	public ArchiveImpl(File file, String parentAbsolutePath, FileFilter filter,
			boolean valid) {
		this.file = file;
		this.parentAbsolutePath = parentAbsolutePath;
		this.filter = filter;
		this.valid = valid;
	}

	public ArchiveImpl(File file, String parentAbsolutePath, FileFilter filter) {
		this(file, parentAbsolutePath, filter, false);
	}

	public ArchiveImpl(File file, String parentAbsolutePath) {
		this(file, parentAbsolutePath, null);
	}

	public ArchiveImpl(File file) {
		this(file, null);
	}

	public ArchiveImpl() {
		this(null, null);
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean getValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isFile() {
		if (file == null) {
			return false;
		}
		//
		return file.isFile();
	}

	public boolean isDirectory() {
		if (file == null) {
			return false;
		}
		//
		return file.isDirectory();
	}

	/**
	 * 完整檔案名稱, 根據父層絕對路徑(parentAbsolutePath)
	 * 
	 * /custom/resource/s1/default/css/style.css
	 * 
	 * @return
	 */
	public String getPath() {
		if (file == null) {
			return null;
		}
		//
		String path = file.getAbsolutePath();
		String relPath = path.substring(parentAbsolutePath.length());
		return relPath.replace(File.separator, "/");
	}

	/**
	 * 檔案路徑, 不包含檔案名稱的路徑
	 * 
	 * /custom/resource/s1/default/css
	 * 
	 * @return
	 */
	public String getParent() {
		String name = getPath();
		if (name == null) {
			return null;
		}
		return name.substring(0, name.lastIndexOf('/'));
	}

	/**
	 * 檔案名稱, 不包含路徑的檔案名稱
	 * 
	 * 如沒有指定名稱，則返回檔自身的名稱
	 * 
	 * style.css
	 * 
	 * @return
	 */
	public String getName() {
		if (file == null) {
			return null;
		}
		//
		return file.getName();
	}

	public String getExtension() {
		return FileHelper.getExtension(file);
	}

	public long getLastModified() {
		if (file == null) {
			return 0;
		}
		//
		return file.lastModified();
	}

	public Date getLastModifiedDate() {
		if (file == null) {
			return null;
		}
		//
		return new Date(file.lastModified());
	}

	public long getLength() {
		if (file == null) {
			return 0L;
		}
		//
		return file.length() / 1024 + 1;
	}

	public String getIcon() {
		if (file == null) {
			return null;
		}
		//
		if (file.isDirectory()) {
			return "folder";
		}
		String ext = getExtension();
		if (ext == null) {
			return "unknow";
		} else {
			ext = ext.toLowerCase(Locale.ENGLISH);
			if (ext.equals("jpg") || ext.equals("jpeg")) {
				return "jpg";
			} else if (ext.equals("png")) {
				return "png";
			} else if (ext.equals("gif")) {
				return "gif";
			} else if (ext.equals("html") || ext.equals("htm")) {
				return "html";
			} else if (ext.equals("swf")) {
				return "swf";
			} else if (ext.equals("txt")) {
				return "txt";
			} else if (ext.equals("sql")) {
				return "sql";
			} else {
				return "unknow";
			}
		}
	}

	public List<Archive> getChild() {
		if (this.child != null) {
			return this.child;
		}
		//
		File[] files = null;
		if (filter == null) {
			files = getFile().listFiles();
		} else {
			files = getFile().listFiles(filter);
		}
		//
		List<Archive> result = new ArrayList<Archive>();
		if (files != null) {
			Arrays.sort(files, new DirComparator());
			for (File file : files) {
				Archive archive = new ArchiveImpl(file, parentAbsolutePath,
						filter);
				result.add(archive);
			}
		}
		return result;
	}

	public void setChild(List<Archive> child) {
		this.child = child;
	}

	public boolean isEditType() {
		boolean result = false;
		//
		String ext = getExtension();
		if (!isDirectory() && ext != null) {
			StringBuilder buff = new StringBuilder(
					ext.toLowerCase(Locale.ENGLISH));
			EditType editExtType = EnumHelper.valueOf(EditType.class,
					buff.toString());
			if (editExtType != null) {
				result = true;
			}
		}
		//
		return result;
	}

	public boolean isImageType() {
		boolean result = false;
		//
		String ext = getExtension();
		if (!isDirectory() && ext != null) {
			StringBuilder buff = new StringBuilder(
					ext.toLowerCase(Locale.ENGLISH));
			ImageType imageType = EnumHelper.valueOf(ImageType.class,
					buff.toString());
			if (imageType != null) {
				result = true;
			}
		}
		//
		return result;

	}

	public boolean equals(Object object) {
		if (!(object instanceof ArchiveImpl)) {
			return false;
		}
		if (this == object) {
			return true;
		}
		ArchiveImpl other = (ArchiveImpl) object;
		return new EqualsBuilder().append(file, other.file).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(file).toHashCode();
	}

	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.appendSuper(super.toString());
		builder.append("file", file);
		builder.append("valid", valid);
		builder.append("parentAbsolutePath", parentAbsolutePath);
		//
		builder.append("path", getPath());
		builder.append("parent", getParent());
		builder.append("name", getName());
		//
		builder.append("extension", getExtension());
		builder.append("lastModified", getLastModified());
		builder.append("size", getLength());
		builder.append("icon", getIcon());
		builder.append("isEditType", isEditType());
		builder.append("isImageType", isImageType());
		return builder.toString();
	}

	public Object clone() {
		ArchiveImpl copy = null;
		copy = (ArchiveImpl) super.clone();
		//
		copy.file = clone(file);
		return copy;
	}
}
