import React from 'react'
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Dropdown, DropdownMenu, DropdownTrigger, DropdownItem } from "@nextui-org/react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCoins, faUser, faGear, faRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import { EoUser } from '../utils/EoUser';

function NavbarComp() {
    return (
        <>
            <Navbar shouldHideOnScroll isBordered className='bg-gray-300 flex justify-center' style={{ boxShadow: "3px 3px 30px 5px rgba(62,66,66,0.35)" }}>
                <NavbarContent className="hidden sm:flex gap-4" justify="center">
                    <NavbarBrand className='flex justify-center items-cente' justify="center">
                        <Link href="/" color='foreground' className='font-semibold text-4xl text-gray-800'>
                            <FontAwesomeIcon icon={faCoins} className='mr-3' />
                            Expenses Overview
                        </Link>
                    </NavbarBrand>
                </NavbarContent>
                {EoUser.isLogged ? (
                    <NavbarContent justify="end">
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Link href="/expenses" color='foreground' className='text-lg' >Expenses</Link>
                        </NavbarItem>
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Link href="/analytics" color='foreground' className='text-lg' >Analytics</Link>
                        </NavbarItem>
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Dropdown className='mt-5'>
                                <DropdownTrigger>
                                    <FontAwesomeIcon icon={faUser} className='text-2xl cursor-pointer' />
                                </DropdownTrigger>
                                <DropdownMenu aria-label="Link Actions">
                                    <DropdownItem key="home" href="/account">
                                        <FontAwesomeIcon icon={faGear} className='mr-3' />
                                        Setting
                                    </DropdownItem>
                                    <DropdownItem key="about" href="/" onClick={() => { EoUser.logout() }} color="danger" className='text-danger'>
                                        <FontAwesomeIcon icon={faRightFromBracket} className='mr-3' />
                                        Logout
                                    </DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                        </NavbarItem>
                    </NavbarContent>
                ) : (
                    <NavbarContent justify="end">
                        <NavbarItem className="hidden lg:flex gap-4">
                            <Link href="/login" color='foreground' className='text-lg'>Login</Link>
                        </NavbarItem>
                        <NavbarItem>
                            <Link href="/signup" color='foreground' className='text-lg'>Sign Up</Link>
                        </NavbarItem>
                    </NavbarContent>
                )}
            </Navbar>
        </>
    );
}

export default NavbarComp;