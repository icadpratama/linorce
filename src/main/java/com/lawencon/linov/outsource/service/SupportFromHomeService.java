package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.util.PageAndSort;
import org.springframework.data.domain.Page;

public interface SupportFromHomeService {

    Page getAllSupportFromHome(PageAndSort model);
}
