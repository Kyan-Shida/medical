package com.ryan.medical.Service;

import com.ryan.medical.pojo.User;

public interface TimeInterface {
    void set_times(User data);

    Integer show_time(String username);
}
