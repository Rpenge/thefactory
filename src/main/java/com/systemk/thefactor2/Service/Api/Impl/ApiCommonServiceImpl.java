//package com.systemk.thefactor2.Service.Api.Impl;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ApiCommonServiceImpl {
//
//    @Transactional(readOnly = true)
////    @Override
//    public List<ApiUserInfoResult> restMemberLogin(String userId, String password, String version, String type, String appType) throws Exception{
//
//        List<ApiUserInfoResult> apiUserInfoList = new ArrayList<ApiUserInfoResult>();
//
//        if(password.equals("") || userId.equals("")){
//            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.BAD_PARAMETER_MESSAGE, ApiResultConstans.BAD_PARAMETER);
//            apiUserInfoList.add(apiUserInfo);
//            return apiUserInfoList;
//        }
//
//        UserInfo user = userInfoRepository.findByUserId(userId);
//
//        if(user == null){
//            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.NOT_FIND_USER_MESSAGE, ApiResultConstans.NOT_FIND_USER);
//            apiUserInfoList.add(apiUserInfo);
//            return apiUserInfoList;
//        }
//
//        if(!passwordEncoder.matches(password, user.getPassword())){
//            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.NOT_VALID_PASSWORD_MESSAGE, ApiResultConstans.NOT_VALID_PASSWORD);
//            apiUserInfoList.add(apiUserInfo);
//            return apiUserInfoList;
//        }
//
//        if(user.getUseYn().equals("N")){
//            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.NOT_USE_USER_MESSAGE, ApiResultConstans.NOT_USE_USER);
//            apiUserInfoList.add(apiUserInfo);
//            return apiUserInfoList;
//        }
//
//        if(user.getCheckYn().equals("N")){
//            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.NOT_CHECK_ADMIN_MESSAGE, ApiResultConstans.NOT_CHECK_ADMIN);
//            apiUserInfoList.add(apiUserInfo);
//            return apiUserInfoList;
//        }
//
//        if(user.getCompanyInfo().getUseYn().equals("N")){
//            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.NOT_USE_COMPANY_MESSAGE, ApiResultConstans.NOT_USE_COMPANY);
//            apiUserInfoList.add(apiUserInfo);
//            return apiUserInfoList;
//        }
//
//
//        if(type.equals("2")){
//            AppInfo appInfo = appService.currentRepresentApp(appType);
//
//            if(appInfo != null){
//
//                if(!version.equals("911") && !version.equals(appInfo.getVersion())){
//                    ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.VERSION_UPDATE_MESSAGE, ApiResultConstans.VERSION_UPDATE);
//                    apiUserInfo.setAppDownloadUrl(env.getProperty("version.download.url"));
//                    apiUserInfoList.add(apiUserInfo);
//                    return apiUserInfoList;
//                }
//
//                switch(appType){
//                    case "1" :
//                        if(!user.getCompanyInfo().getRoleInfo().getRole().equals("production")){
//                            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.ROLE_VIOLATION_MESSAGE, ApiResultConstans.ROLE_VIOLATION);
//                            apiUserInfoList.add(apiUserInfo);
//                            return apiUserInfoList;
//                        }
//                        break;
//                    case "2" :
//                        if(!user.getCompanyInfo().getRoleInfo().getRole().equals("distribution")){
//                            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.ROLE_VIOLATION_MESSAGE, ApiResultConstans.ROLE_VIOLATION);
//                            apiUserInfoList.add(apiUserInfo);
//                            return apiUserInfoList;
//                        }
//                        break;
//
//                    case "3" :
//                        if(!(user.getCompanyInfo().getRoleInfo().getRole().equals("sales") || user.getCompanyInfo().getRoleInfo().getRole().equals("special"))){
//                            ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.ROLE_VIOLATION_MESSAGE, ApiResultConstans.ROLE_VIOLATION);
//                            apiUserInfoList.add(apiUserInfo);
//                            return apiUserInfoList;
//                        }
//                        break;
//
//                }
//            }
//
//        }
//
//        UserDetails userDetail = customUserDetailService.loadUserByUsername(user.getUserId());
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                userDetail, user.getPassword(),
//                userDetail.getAuthorities());
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//
//
//        ApiUserInfoResult apiUserInfo = new ApiUserInfoResult(ApiResultConstans.LOGIN_SUCCESS_MESSAGE, ApiResultConstans.SUCCESS, user);
//        apiUserInfoList.add(apiUserInfo);
//
//        return apiUserInfoList;
//    }
//}
